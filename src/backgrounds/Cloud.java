package backgrounds;

import java.awt.Color;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;

/**
 * The "Cloud" Class; responsible for a cloud sprite.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Cloud implements Sprite {

    private Point location;
    private Color color;
    private int size;

    /**
     * The constructor.
     *
     * @param location the location of the cloud
     * @param color    the color of the cloud
     * @param size     the size of the cloud
     */
    public Cloud(Point location, Color color, int size) {
        this.color = color;
        this.location = location;
        this.size = size;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        for (int i = 0; i < size; i++) {
            d.fillCircle(14 * i + (int) location.getX(), (int) location.getY(), 10);
        }
        for (int j = 0; j < size - 1; j++) {
            d.fillCircle(7 + 14 * j + (int) location.getX(), (int) location.getY() - 10, 10);
        }

    }

    @Override
    public void timePassed() {
        location = new Point(location.getX() + 1, location.getY());
        if (location.getX() > 800) {
            location = new Point(-10, location.getY());
        }
    }

}
