package geometry;

import java.util.LinkedList;

/**
 * The "Line" Class; represents a line with a start point and an end point.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Line {

    private static final double EPSILON = 0.000001;
    private Point start; // starting point
    private Point end; // ending point

    /**
     * Constructor: start and end points to create a line instance.
     *
     * @param start point
     * @param end   point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor: x and y for start point and x and y for end point to create 2
     * points then a line.
     *
     * @param x1 coordinate for start point
     * @param y1 coordinate for start point
     * @param x2 coordinate for end point
     * @param y2 coordinate for end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * This method is used to add two calculate the length of the line.
     *
     * @return double the length
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * This method is used to calculate the middle of the line.
     *
     * @return Point the middle point
     */
    public Point middle() {
        double mx = (this.start.getX() + this.end.getX()) / 2;
        double my = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(mx, my);
        return middle;
    }

    /**
     * This method is used to return the start point of the line.
     *
     * @return Point the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method is used to return the start point of the line.
     *
     * @return Point the start point
     */
    public Point end() {
        return this.end;
    }

    /**
     * This function returns the closest intersection of this line with a rectangle.
     *
     * @param rect the rectangle
     * @return Point closest the intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        LinkedList<Point> intersections = new LinkedList<Point>();
        intersections.addAll(rect.intersectionPoints(this));
        Point closest = null;
        if (intersections.size() > 0) {
            closest = intersections.get(0);
        }
        for (Point inter : intersections) {
            if (inter != null && inter.distance(this.start) < closest.distance(this.start)) {
                closest = inter;
            }
        }
        return closest;
    }

    /**
     * This method is used to check if the line is intersecting with another given
     * line.
     *
     * @param other the other line
     * @return Point the start point
     */
    public boolean isIntersecting(Line other) {
        Point inter = null;
        /*
         * Go through all cases, if the line is vertical, if the lines end is equal to
         * its start, and a normal line.
         */

        if (this.isVertical() && !other.isVertical()) {
            inter = this.intersectionPoint(other);
        }
        if (!this.isVertical() && other.isVertical()) {
            inter = this.intersectionPoint(other);
        }
        if (!this.isPoint() && !other.isPoint()) {
            if (this.m() != other.m()) {
                inter = this.intersectionPoint(other);
            }
        }
        if (!this.isPoint() && other.isPoint()) {
            inter = this.intersectionPoint(other);
        }
        if (this.isPoint() && !other.isPoint()) {
            inter = this.intersectionPoint(other);
        }
        if (this.isPoint() && other.isPoint()) {
            inter = this.intersectionPoint(other);
        }
        /*
         * We found the intersection point assuming that the lines continue forever, now
         * we need to check if the intersection point is inside the line(between the
         * start and the end) to verify that it is a valid intersection.
         */
        if (inter != null && inLineRange(inter, this) && inLineRange(inter, other)) {
            return true;
        }
        return false;
    }

    /**
     * This function decides if a intersection point between 2 lines is in the range
     * of a given line (there can be a collision point that occurs after the line
     * ends).
     *
     * @param p the intersection point
     * @param l the line
     * @return boolean in range or not
     */
    private boolean inLineRange(Point p, Line l) {

        if (p.getX() >= l.start.getX() - EPSILON && p.getX() <= l.end.getX() + EPSILON
                && p.getY() + EPSILON >= l.start.getY() && p.getY() <= l.end.getY() + EPSILON) {
            return true;
        }
        if (p.getX() <= l.start.getX() + EPSILON && p.getX() + EPSILON >= l.end.getX()
                && p.getY() <= l.start.getY() + EPSILON && p.getY() + EPSILON >= l.end.getY()) {
            return true;
        }
        if (p.getX() + EPSILON >= l.start.getX() && p.getX() <= l.end.getX() + EPSILON
                && p.getY() <= l.start.getY() + EPSILON && p.getY() + EPSILON >= l.end.getY()) {
            return true;
        }
        if (p.getX() <= l.start.getX() + EPSILON && p.getX() + EPSILON >= l.end.getX()
                && p.getY() + EPSILON >= l.start.getY() && p.getY() <= l.end.getY() + EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to find the intersection point of this line with another.
     * if there is no intersection point then null is returned.
     *
     * @param other the other line
     * @return Point the intersection point or null
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return this.intersectionPoint(other);
        }
        return null;
    }

    /**
     * This method is used to find an intersection point between this line and
     * another assuming that the lines continue forever, and have no starting or
     * ending point. or null if there is no intersection (ex. parallel)
     *
     * @param other the other line
     * @return Point the intersection point or null
     */
    private Point intersectionPoint(Line other) {
        /*
         * if 1 of the lines are vertical, the x is the x of the vertical line and the y
         * if found by finding the y value of the other line at x.
         */
        if (this.isVertical() && !other.isVertical()) {
            Point inter = new Point(this.start().getX(), other.m() * this.start().getX() + other.b());
            return inter;
        }
        if (!this.isVertical() && other.isVertical()) {
            Point inter = new Point(other.start().getX(), this.m() * other.start().getX() + this.b());
            return inter;
        }

        /*
         * if the lines are standard and not vertical; the point is found by comparing
         * the 2 lines' equations (which are constructed of m and b) to find the x then
         * plugging in one of the equations to find the y.
         */
        if (!this.isPoint() && !other.isPoint()) {
            double interX = solveEquation(this.m(), other.m(), this.b(), other.b());
            double interY = this.m() * interX + this.b();
            Point inter = new Point(interX, interY);
            return inter;
        }
        /*
         * If one of the lines start and finish at the same point, so enough to check if
         * the start ( or end) point is on the second line. to do that we'll plug the x
         * and y of the point into the line's equation and see if it's correct.
         */
        if (!this.isPoint() && other.isPoint()) {
            if (Math.abs(other.start().getY() - (this.m() * other.start().getX() + this.b())) <= EPSILON) {
                Point inter = new Point(other.start().getX(), other.start().getY());
                return inter;
            }
        }
        if (this.isPoint() && !other.isPoint()) {
            if (Math.abs(this.start().getY() - (other.m() * this.start().getX() + other.b())) <= EPSILON) {
                Point inter = new Point(this.start().getX(), this.start().getY());
                return inter;
            }
        }
        /*
         * If the two lines start and finish at the same point, so we can check if the
         * start (or end) of the first point is equal to the other.
         */
        if (this.isPoint() && other.isPoint()) {
            if (this.equals(other)) {
                Point inter = new Point(other.start().getX(), other.start().getY());
                return inter;
            }
        }
        return null;
    }

    /**
     * This method is used to check is a line is vertical.
     *
     * @return boolean is it vertical
     */
    public boolean isVertical() {
        if (Math.abs(this.start().getX() - this.end().getX()) <= EPSILON && this.start().getY() != this.end().getY()) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to check if the line is actually a point (if the start
     * equals to the end).
     *
     * @return boolean is it a point
     */
    public boolean isPoint() {
        if (this.start.equals(this.end)) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to find the angle of a line (if it has an angle).
     *
     * @return m angle
     */
    public double m() {
        if (this.end.getX() - this.start.getX() != 0) {
            return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        }
        return -1;
    }

    /**
     * This method is used to find the b of a line equation.
     *
     * @return double the b
     */
    public double b() {
        return this.start.getY() + -this.start.getX() * this.m();
    }

    /**
     * This method to solve the x of an equation. m1x+b1=m2x+b2 (2 linear line
     * equations)
     *
     * @param m1 the first m
     * @param m2 the second m
     * @param b1 the first b
     * @param b2 the second b
     * @return the x result
     */
    public static double solveEquation(double m1, double m2, double b1, double b2) {
        if (m1 - m2 != 0) {
            return (b2 - b1) / (m1 - m2);
        }
        return -1;
    }

    /**
     * This method is used to check if this line and another are equal.
     *
     * @param other the other line
     * @return boolean if the lines are equal
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

}