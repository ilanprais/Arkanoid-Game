package geometry;

import java.util.LinkedList;

/**
 * The "Rectangle" Class; representing a rectangle with with it's upper left
 * point, width, and height.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * The constructor.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;

    }

    /**
     * This function returns a possibly empty list of intersection points with the
     * specified line.
     *
     * @param line the intersecting line
     * @return java.util.List<Point> list of intersections
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        LinkedList<Point> intersections = new LinkedList<Point>();

        Line[] sides = new Line[4];
        sides = this.sides();

        for (Line side : sides) {
            if (line.isIntersecting(side)) {
                Point iP = line.intersectionWith(side);
                if (!includes(intersections, iP)) {
                    intersections.add(line.intersectionWith(side));
                }
            }
        }

        return intersections;
    }

    /**
     * This function returns the 4 sides of this rectangle.
     *
     * @return Line[] array of the 4 sides of the rectangle
     */
    public Line[] sides() {

        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        Line[] sides = new Line[4];
        sides[0] = new Line(this.upperLeft, upperRight); // top
        sides[1] = new Line(lowerLeft, lowerRight); // botoom
        sides[2] = new Line(upperRight, lowerRight); // right
        sides[3] = new Line(this.upperLeft, lowerLeft); // left

        return sides;
    }

    /**
     * This function returns if a Point is in a list of Points.
     *
     * @param others list of Points
     * @param p      the Point
     * @return boolean the list includes the same point
     */
    private boolean includes(java.util.List<Point> others, Point p) {
        for (Point other : others) {
            if (other.equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return double the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return double the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Point the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set the upper left point.
     *
     * @param p the upper left point
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}