package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.Sprite;
import interfaces.HitNotifier;
import maingame.GameLevel;
import other.Velocity;

/**
 * The "Block" Class; representing a Block with a rectangle.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */

public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle shape;
    private Color color;
    private List<HitListener> hitListeners;
    private int destroyPoints = 10;

    /**
     * The constructor.
     *
     * @param shape the rectangle which builds the block.
     */
    public Block(Rectangle shape) {
        this.shape = shape;
        hitListeners = new ArrayList<>();
        this.color = null;
    }

    /**
     * This function sets the color to the ball.
     *
     * @param c the color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * This function returns the amount of points added from destroying this block.
     *
     * @return the amount of points
     */
    public int getDestroyPoints() {
        return this.destroyPoints;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = currentVelocity;
        Line[] sides = this.shape.sides(); // The 4 sides of the triangle array

        /*
         * Check if the intersection point is on the top/bottom, or on the sides, and
         * change the velocity accordingly.
         */
        if (sides[0].isIntersecting(new Line(collisionPoint, collisionPoint))
                || sides[1].isIntersecting(new Line(collisionPoint, collisionPoint))) {
            newV = new Velocity(newV.getDx(), -1 * newV.getDy());
            this.notifyHit(hitter);
        }
        if (sides[2].isIntersecting(new Line(collisionPoint, collisionPoint))
                || sides[3].isIntersecting(new Line(collisionPoint, collisionPoint))) {
            newV = new Velocity(-1 * newV.getDx(), newV.getDy());
            this.notifyHit(hitter);
        }

        return newV;
    }

    /**
     * This function adds the block to a game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This function removes the block from the game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * This function adds hl as a listener to hit events.
     *
     * @param hl the listener
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * This function removes hl from the list of listeners to hit events.
     *
     * @param hl the listener
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * This function Notifies all listeners about a hit event.
     *
     * @param hitter the ball hitting
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (this.color == null) {
            drawOnByLayer(surface);
        } else {
            drawOnByColor(surface, this.color);
        }
    }

    /**
     * This function draws the block in a certain color, according to its y
     * coordinate (layer).
     *
     * @param surface the draw surface
     */
    public void drawOnByLayer(DrawSurface surface) {
        int x = (int) (this.getCollisionRectangle().getUpperLeft().getX());
        int y = (int) (this.getCollisionRectangle().getUpperLeft().getY());
        int width = (int) this.getCollisionRectangle().getWidth();
        int height = (int) this.getCollisionRectangle().getHeight();

        if (x == 0 || x == 800 - 20) {
            surface.setColor(Color.DARK_GRAY);
            surface.fillRectangle(x, y, width, height);
            surface.setColor(Color.BLACK);
            surface.drawRectangle(x, y, width, height);
        } else if (y == 20) {
            surface.setColor(Color.LIGHT_GRAY);
            surface.fillRectangle(x, y, width, height);
            surface.setColor(Color.BLACK);
            surface.drawRectangle(x, y, width, height);
        } else {
            surface.setColor(randomColorByLayer(y));
            surface.fillRectangle(x, y, width, height);
            surface.setColor(Color.BLACK);
            surface.drawRectangle(x, y, width, height);
        }
    }

    /**
     * This function draws the block in a given color.
     *
     * @param c       the color
     * @param surface the draw surface
     */
    public void drawOnByColor(DrawSurface surface, Color c) {
        int x = (int) (this.getCollisionRectangle().getUpperLeft().getX());
        int y = (int) (this.getCollisionRectangle().getUpperLeft().getY());
        int width = (int) this.getCollisionRectangle().getWidth();
        int height = (int) this.getCollisionRectangle().getHeight();

        surface.setColor(c);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
        surface.setColor(Color.WHITE);

    }

    /**
     * Receives a y coordinate and returns the color of the layer it is in.
     *
     * @param y y coordinate.
     * @return Color the color to color the block
     */
    private Color randomColorByLayer(int y) {
        int a = (int) (random(y, y) * 256);
        int b = (int) (random(2 * y, y) * 256);
        int c = (int) (random(3 * y, y) * 256);
        return new Color(a, b, c);
    }

    /**
     * This function returns a random number that is depending on 2 other numbers -
     * a sort of function resulting in a new value from x and y.
     *
     * @param x value
     * @param y value
     * @return float random value depending on x and y.
     */
    public static float random(int x, int y) {
        int n = x + y * 57;
        n = (n << 13) ^ n;
        return Math.abs((1.0f - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7FFFFFFF) / 1073741824.0f));
    }

    /**
     * this function notifies the block that the time has passed however the block
     * is constant so nothing happens.
     */
    public void timePassed() {
    };

}
