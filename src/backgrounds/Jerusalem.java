package backgrounds;

import java.awt.Color;

import biuoop.DrawSurface;
import interfaces.Sprite;

/**
 * The "Jerusalem" Class; responsible for the Jerusalem background.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Jerusalem implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        drawBackground(d);
        drawTower(d, 390, 600);
        drawSun(d);
        drawMountains(d);
        drawDome(d, 650, 503);
        drawTower(d, 30, 501);
    }

    @Override
    public void timePassed() {

    }

    /**
     * This function draws the background on a draw surface.
     *
     * @param d the draw surface
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(new Color(0, 191, 255));
        d.fillRectangle(20, 20, 760, 580);
    }

    /**
     * This functions draws the sun.
     *
     * @param d the draw surface
     */
    private void drawSun(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillCircle(0, 0, 80);
        d.drawLine(20, 40, 800, 50);
        d.drawLine(20, 40, 800, 75);
        d.drawLine(20, 40, 600, 100);
        d.drawLine(20, 40, 400, 125);
        d.drawLine(20, 40, 200, 150);
        d.drawLine(20, 40, 50, 175);
    }

    /**
     * This functions draws the mountains.
     *
     * @param d the draw surface
     */
    private void drawMountains(DrawSurface d) {
        d.setColor(new Color(237, 201, 175));
        d.fillCircle(80, 700, 200);
        d.fillCircle(280, 700, 180);
        d.fillCircle(450, 700, 220);
        d.fillCircle(680, 700, 190);
        d.fillCircle(860, 700, 200);
    }

    /**
     * This functions draws the dome.
     *
     * @param d the draw surface
     * @param x the x location
     * @param y the y location
     */
    private void drawDome(DrawSurface d, int x, int y) {
        d.setColor(new Color(207, 181, 59));
        d.fillCircle(x + 30, y - 19, 16);
        d.setColor(Color.WHITE);
        d.fillRectangle(x, y, 60, 10);
        d.setColor(new Color(65, 105, 225));
        d.fillRectangle(x, y - 10, 60, 10);
        d.fillRectangle(x + 12, y - 19, 35, 10);
        d.drawLine(x + 30, y - 35, x + 30, y - 40);

    }

    /**
     * This functions draws the tower.
     *
     * @param d the draw surface
     * @param x the x location
     * @param y the y location
     */
    private void drawTower(DrawSurface d, int x, int y) {
        d.setColor(Color.WHITE);
        d.fillRectangle(x, y, 100, 3);

        for (int j = 0; j < 10; j++) {
            d.drawLine(x + 75, y - 110, x + 10 * j, y);
        }

        for (int i = 0; i < 6; i++) {
            d.drawLine(x + 50 + i, y, x + 70 + i, y - 50);
            d.drawLine(x + 70 + i, y - 50, x + 75, y - 110);
        }

    }

}
