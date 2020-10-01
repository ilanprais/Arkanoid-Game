package backgrounds;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
/**
 * The "Paris" Class; responsible for the Paris background.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Paris implements Sprite {

    private List<Cloud> clouds;

    /**
     * The constructor.
     */
    public Paris() {
        initClouds();
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawBackground(d);
        drawClouds(d);
        drawTower(d, 390, 580);
        drawGround(d);

    }

    @Override
    public void timePassed() {
        for (Cloud c : clouds) {
            c.timePassed();
        }
    }

    /**
     * This functions draws the background.
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
        for (int i = 0; i < 3; i++) {
            clouds.add(new Cloud(new Point(300 * i, 70), Color.WHITE, 4));
        }
    }

    /**
     * This functions draws the clouds.
     *
     * @param d the draw surface
     */
    private void drawClouds(DrawSurface d) {
        for (Cloud c : clouds) {
            c.drawOn(d);
        }
    }

    /**
     * This functions draws the ground.
     *
     * @param d the draw surface
     */
    private void drawGround(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(20, 590, 760, 10);
    }

    /**
     * This function draws the tower in a given location.
     *
     * @param d the draw surface
     * @param x the x location
     * @param y the y location
     */
    private void drawTower(DrawSurface d, int x, int y) {

        d.setColor(new Color(152, 140, 129));
        d.drawCircle(x + 20, y, 60);
        d.fillRectangle(x - 80, y - 3, 40, 3);
        d.fillRectangle(x + 80, y - 3, 40, 3);
        d.drawLine(x - 80, y - 3, x - 35, y - 80);
        d.drawLine(x - 50, y - 3, x - 5, y - 80);
        d.drawLine(x + 120, y - 3, x + 75, y - 80);
        d.drawLine(x + 90, y - 3, x + 45, y - 80);

        d.drawLine(x - 65, y - 30, x - 35, y - 30);
        d.drawLine(x + 105, y - 30, x + 75, y - 30);
        d.drawLine(x - 50, y - 55, x - 20, y - 55);
        d.drawLine(x + 90, y - 55, x + 60, y - 55);

        d.fillRectangle(x - 35, y - 85, 110, 5);
        d.fillRectangle(x - 40, y - 92, 120, 7);
        d.drawLine(x - 30, y - 92, x - 5, y - 162);
        d.drawLine(x + 70, y - 92, x + 45, y - 162);
        d.drawLine(x - 5, y - 92, x + 15, y - 162);
        d.drawLine(x + 45, y - 92, x + 25, y - 162);
        d.drawLine(x + 70, y - 92, x + 45, y - 162);

        d.drawLine(x - 23, y - 114, x, y - 114);
        d.drawLine(x + 62, y - 114, x + 38, y - 114);
        d.drawLine(x - 13, y - 140, x + 8, y - 140);
        d.drawLine(x + 52, y - 140, x + 30, y - 140);

        d.fillRectangle(x - 5, y - 162, 51, 4);
        d.fillRectangle(x - 8, y - 168, 57, 6);
        d.drawLine(x - 3, y - 168, x + 18, y - 330);
        d.drawLine(x + 43, y - 168, x + 22, y - 330);
        d.drawLine(x + 20, y - 168, x + 20, y - 330);

        for (int i = 0; i < 150; i += 15) {
            d.drawLine(x - 3 + 2 * i / 15, y - 168 - i, x + 43 - 2 * i / 15, y - 168 - i);
        }

        d.fillCircle(x + 20, y - 330, 4);
        d.fillRectangle(x + 17, y - 330, 6, 6);
        d.setColor(Color.WHITE);
        d.drawLine(x + 20, y - 334, x + 20, y - 341);
    }

}
