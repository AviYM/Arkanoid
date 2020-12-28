package objects;

import biuoop.DrawSurface;
import collection.Collidable;
import collection.HitListener;
import collection.HitNotifier;
import collection.Sprite;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class defines the block as a Rectangle with Color and with life expectancy(numOfHits).
 * and includes methods that adds a new block to the game, draw a block.
 * and method that changes the ball Velocity when it hits the block.
 * @version 1.0 15 April 2018
 * @author Avi miletzky
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;
    private Map<Integer, Background> backgroundOfEachHit;
    private Background background;
    private Stroke stroke;
    private Integer numOfHits;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Point hitPoint;

    /**
     * This method creates a new block at a given position in the rect.
     * @param rect - the given Rectangle.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.background = null;
        this.stroke = null;
        this.backgroundOfEachHit = null;
        this.numOfHits = null;
    }

    /**
     * This method creates a new block at the position X and Y in the axial system,
     * at a given width and height.
     * @param x - Position on the X axis
     * @param y - Position on the Y axis
     * @param width - the given width.
     * @param height - the given height.
     */
    public Block(double x, double y, double width, double height) {
        this(new Rectangle(new Point(x, y), width, height));
    }

    /**
     * This method used as a copy constructor, and replicates a given block.
     * @param block - a given block.
     */
    public Block(Block block) {
        this.rect = block.rect;
        this.backgroundOfEachHit = block.backgroundOfEachHit;
        this.background = block.background;
        this.stroke = block.stroke;
        this.numOfHits = block.numOfHits;
        this.hitListeners = block.hitListeners;
        this.hitPoint = block.hitPoint;
    }

    /**
     * This method sets a given map of Backgrounds Of Each Hit.
     * @param m - a given map.
     */
    public void setBackgroundOfEachHit(Map<Integer, Background> m) {
        this.backgroundOfEachHit = m;
    }

    /**
     * This method sets a given Background.
     * @param bg - a given Background.
     */
    public void setBackground(Background bg) {
        this.background = bg;
    }

    /**
     * This method sets a given Stroke.
     * @param s - a given Stroke
     */
    public void setStroke(Stroke s) {
        this.stroke = s;
    }

    /**
     * This method sets the numOfHits.
     * @param num - a given number.
     */
    public void setNumOfHits(int num) {
        this.numOfHits = num;
    }

    /**
     * This method Return the "collision shape" of the block.
     * @return the sides of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method Return the Point of hit with the block.
     * @return this.hitPoint
     */
    public Point getHitPoint() {
        return this.hitPoint;
    }

    /**
     * This method Returns the Point of hit with the block.
     * @return this.hitPoint
     */
    public int getNumOfHits() {
        return this.numOfHits;
    }

    /**
     * This method Returns the width of the block.
     * @return this.rect.getWidth()
     */
    public double getWidth() {
        return this.rect.getWidth();
    }

    /**
     * This method Checks where the collision point is located on the sides of the rectangle.
     * and returns the new velocity expected after the hit(based on the current velocity).
     * @param hitter - a given hitter
     * @param collisionPoint - a given collision point
     * @param currentVelocity - a given velocity
     * @return the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hitPoint = collisionPoint;
        int x = 1, y = 1;
        if (this.rect.getSidesArr()[0].checkPointInRangeOfLine(collisionPoint)
                || this.rect.getSidesArr()[2].checkPointInRangeOfLine(collisionPoint)) {
            x = -1;
        }
        if (this.rect.getSidesArr()[1].checkPointInRangeOfLine(collisionPoint)
                || this.rect.getSidesArr()[3].checkPointInRangeOfLine(collisionPoint)) {
            y = -1;
        }
        if (this.numOfHits != null && this.numOfHits > 0) {
            this.numOfHits--;
        }
        this.notifyHit(hitter);
        return new Velocity(x * currentVelocity.getVelocityDx(),
                y * currentVelocity.getVelocityDy());
    }

    /**
     * This method draws block on the window of the game by DrawSurface.
     * and indicates the life span of the block. in case it is equal to 0 marks the block in X.
     * @param d - the given draw surface.
     */
    // draw the sprite to the screen
    public void drawOn(DrawSurface d) {
        if ((this.numOfHits == null && this.background == null)
                || (this.numOfHits != null && this.backgroundOfEachHit == null)) {
            throw new RuntimeException("The block background or numOfHits is not defined");
        } else if (this.numOfHits == null) {
            this.background.setRectangle(this.rect);
            this.background.drawOn(d);
        } else {
            Background bg = this.backgroundOfEachHit.get(this.numOfHits);
            bg.setRectangle(this.rect);
            bg.drawOn(d);
        }
        if (this.stroke != null) {
            this.stroke.draw(d, this.rect);
        }
    }

    /**
     * This method is empty now.
     * @param dt -
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * This method adds THIS paddle to the game.
     * @param g - The given Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This method removes block from the game.
     * @param game - The given Game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * This method adds hl to the list of listeners to hit events.
     * @param hl - The given hitListeners.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method removes hl from the list of listeners to hit events.
     * @param hl - The given hitListeners.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method will be called whenever a hit() occurs,
     * and notifiers all of the registered HitListener objects about the hit.
     * @param hitter - The Ball that's doing the hitting.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}