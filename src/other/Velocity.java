package other;

import geometry.Point;

/**
 * The "Velocity" Class; represents a velocity with a change in x and y.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * The constructor.
     *
     * @param dx The change in x every step
     * @param dy The change in y every step
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This function constructs a new velocity from an angle and a speed.
     *
     * @param angle the angle of the movement
     * @param speed the speed
     * @return Velocity a velocity instance representing the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = speed * Math.cos(Math.toRadians(angle + 180));
        double dx = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * @return double the change in x
     */
    public double getDx() {
        return dx;
    }

    /**
     * This functions sets the change in x.
     *
     * @param dx1 the change
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * @return double the change in y
     */
    public double getDy() {
        return dy;
    }

    /**
     * This functions sets the change in y.
     *
     * @param dy1 the change
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * This function takes a point with position (x,y) and return a new point with
     * the position (x+dx, y+dy).
     *
     * @param p the point
     * @return Point the new point
     */
    public Point applyToPoint(Point p) {
        Point newP = new Point(p.getX() + dx, p.getY() + dy);
        return newP;
    }
}