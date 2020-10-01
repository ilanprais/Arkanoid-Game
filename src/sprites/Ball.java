package sprites;

import java.awt.Color;
import biuoop.DrawSurface;
import collections.GameEnvironment;
import geometry.Line;
import geometry.Point;
import other.CollisionInfo;
import other.Velocity;
import interfaces.Sprite;
import maingame.GameLevel;

/**
 * The "Ball" Class; representing a ball by a center, a radius, a color, and a
 * velocity.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Ball implements Sprite {

    private Point center;
    private int size;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    /**
     * The constructor.
     *
     * @param center the center of the circle
     * @param r      the radius
     * @param color  the fill color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.v = new Velocity(0, 0); // initializing the velocity to 0
    }

    /**
     * The constructor.
     *
     * @param x     the x of the center of the circle
     * @param y     the y of the center of the circle
     * @param r     the radius
     * @param color the fill color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * Setter for the game environment.
     *
     * @param ge the game environment related to the ball
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }

    /**
     * @return integer the center point x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return integer the center point y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This function sets a new location for the ball.
     *
     * @param p the new center of the ball
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * @return integer the size (radius) of the circle.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return Color the fill color of the circle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This function is used to set the velocity to the ball.
     *
     * @param velocity the set velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * This function is used to set the velocity to the ball.
     *
     * @param dx change of x.
     * @param dy change of y.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return Velocity the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * This function moves the ball one step according to the velocity. and
     * calculates if a collision is going to happen. if so, it adjusts the velocity
     * accordingly.
     */
    public void moveOneStep() {

        /*
         * calculating the trajectory
         */
        Point newLoc = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, newLoc);
        Point targetLoc = newLoc;

        /*
         * checking for a collision
         */
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);

        /*
         * If there is a collision, then move the ball nearly to the collision point and
         * change it's speed according to the hit side.
         */
        if (collision != null) {

            double x = (trajectory.start().getX() + 3 * collision.collisionPoint().getX()) / 4;
            double y = (trajectory.start().getY() + 3 * collision.collisionPoint().getY()) / 4;

            targetLoc = new Point(x, y);

            this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);
        }

        /*
         * Return the new location the ball should go to.
         */
        this.center = targetLoc;

    }

    /**
     * This function is called when the "time had passed" and the ball should move
     * to it's next position.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This function add this ball to a game object, to make it part of the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This function removes this ball from a game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * This function draws the ball on a given surface.
     *
     * @param surface the draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
        surface.setColor(Color.WHITE);
        surface.drawCircle(this.getX(), this.getY(), this.size);
    }
}