package backgrounds;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;

/**
 * The "Berlin" Class; responsible for the Berlin background.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Berlin implements Sprite {

    private List<Cloud> clouds;

    /**
     * The constructor.
     */
    public Berlin() {
        initClouds();
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawBackground(d);
        drawClouds(d);
        drawTower(d, 390, 600);

    }

    @Override
    public void timePassed() {
        for (Cloud c : clouds) {
            c.timePassed();
        }
    }

    /**
     * This function draws the background.
     *
     * @param d the draw surface
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(new Color(0, 191, 255));
        d.fillRectangle(20, 20, 760, 580);
    }

    /**
     * This function initializes the clouds.
     */
    private void initClouds() {
        clouds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            clouds.add(new Cloud(new Point(200 * i, 70), Color.WHITE, 4));
        }
    }

    /**
     * This function draws the clouds on the drawSurface.
     *
     * @param d the draw surface
     */
    private void drawClouds(DrawSurface d) {
        for (Cloud c : clouds) {
            c.drawOn(d);
        }
    }

    /**
     * This function draws the tower on a draw surface.
     *
     * @param d the draw surface
     * @param x the x location
     * @param y the y location
     */
    private void drawTower(DrawSurface d, int x, int y) {

        d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 350; i++) {
            d.drawRectangle(x + i / 40, y - i, 28 - 2 * i / 40, 1);
        }

        d.setColor(Color.GRAY);
        d.drawRectangle(x + 4, y - 330, 20, 1);
        d.setColor(Color.LIGHT_GRAY);
        d.drawRectangle(x + 4, y - 331, 20, 1);
        d.drawRectangle(x + 4, y - 332, 20, 1);
        d.setColor(Color.GRAY);
        d.drawRectangle(x + 4, y - 333, 20, 1);

        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(x + 5, y - 440, 20, 40);

        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(x + 5, y - 440, 20, 10);

        d.setColor(Color.GRAY);
        d.fillCircle(x + 15, y - 375, 30);

        for (int j = 0; j < 100; j += 5) {
            if (j % 2 == 0) {
                d.setColor(Color.WHITE);
            } else {
                d.setColor(Color.RED);
            }
            d.drawLine(x + 15, y - 440 - j, x + 15, y - 440 - j - 5);
        }

    }

}
