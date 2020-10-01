package geometry;

/**
 * The "Point" Class; represents a point with x and y coordinates.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Point {

    private static final double EPSILON = 0.0000001;
    private double x; // x value
    private double y; // y value

    /**
     * Constructor: x and y coordinate to create a point instance.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method is used to add two calculate the distance between this point and
     * another point.
     *
     * @param other The other point to measure the distance to
     * @return double the distance
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * This method is used to check if this point is equal to another. the distance
     * between this point and another point.
     *
     * @param other The other point
     * @return boolean if they are equal
     */
    public boolean equals(Point other) {
        if (Math.abs(this.x - other.x) <= EPSILON && Math.abs(this.y - other.y) <= EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to get the x value of the point.
     *
     * @return double the x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method is used to get the y value of the point.
     *
     * @return double the y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method is used to get the string representing the point.
     *
     * @return double the x value
     */
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}