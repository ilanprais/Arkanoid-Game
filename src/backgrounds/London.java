package backgrounds;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;

/**
 * The "London" Class; responsible for the London background.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class London implements Sprite {

    private int bigBenClockAngle;
    private int bigWheelAngle;
    private int rainTime;
    private List<Cloud> clouds;

    /**
     * The constructor.
     */
    public London() {
        bigBenClockAngle = 180;
        rainTime = 0;
        bigWheelAngle = 0;

        clouds = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            clouds.add(new Cloud(new Point(100 * i, 70), Color.GRAY, 4));
        }

    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(new Color(0, 191, 255));
        d.fillRectangle(20, 20, 760, 580);

        /*
         * Big Ben
         */
        drawBigBen(d, new Point(50, 580), bigBenClockAngle);

        /*
         * Rain
         */
        for (int i = 0; i < 50; i++) {
            if (rainTime - 50 * i < 600 && rainTime - 50 * i > 0) {
                drawRain(d, rainTime - 50 * i);
            }

            if (rainTime > 2000) {
                rainTime = 600;
            }
        }

        /*
         * Big Wheel
         */
        drawBigWheel(d, bigWheelAngle, new Point(600, 600));

        /**
         * Clouds
         */
        drawClouds(d);
    }

    @Override
    public void timePassed() {
        bigBenClockAngle--;
        rainTime += 5;
        bigWheelAngle++;
        for (Cloud c : clouds) {
            c.timePassed();
        }

    }

    /**
     * This function draws the big ben on a given draw surface.
     *
     * @param d          the draw surface
     * @param bottomLeft the point for the location
     * @param deg        the clock degrees
     */
    private void drawBigBen(DrawSurface d, Point bottomLeft, int deg) {
        Color brown = new Color(210, 180, 140);
        Color brownGray = new Color(144, 120, 96);
        Color lightBrown = new Color(222, 184, 135);
        Color darkBrown = new Color(205, 133, 63);
        Color darkWhite = new Color(250, 235, 215);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 6; j++) {
                if (i % 4 == 0 && i != 0) {
                    d.setColor(brown);
                    if (j == 0) {
                        d.fillRectangle((int) bottomLeft.getX() - 5, (int) bottomLeft.getY() - 20 * i, 18, 20);
                    } else if (j == 5) {
                        d.fillRectangle((int) bottomLeft.getX() + 13 * 5, (int) bottomLeft.getY() - 20 * i, 18, 20);
                    } else {
                        d.fillRectangle((int) bottomLeft.getX() + 13 * j, (int) bottomLeft.getY() - 20 * i, 13, 20);
                    }
                    d.setColor(Color.BLACK);
                    d.fillCircle((int) bottomLeft.getX() + 13 * j + 5, (int) bottomLeft.getY() - 20 * i + 10, 2);
                } else {

                    d.setColor(brown);
                    d.fillRectangle((int) bottomLeft.getX() + 13 * j, (int) bottomLeft.getY() - 20 * i, 13, 20);
                    d.setColor(brownGray);
                    d.drawRectangle((int) bottomLeft.getX() + 13 * j, (int) bottomLeft.getY() - 20 * i, 13, 20);
                }
            }
        }

        d.setColor(lightBrown);
        d.fillRectangle((int) bottomLeft.getX() - 10, (int) bottomLeft.getY() - 390, 13 * 6 + 20, 13 * 7);
        d.setColor(brownGray);
        d.drawRectangle((int) bottomLeft.getX() - 10, (int) bottomLeft.getY() - 390, 13 * 6 + 20, 13 * 7);
        d.setColor(darkBrown);
        d.fillRectangle((int) bottomLeft.getX() + 5, (int) bottomLeft.getY() - 377, 13 * 4 + 17, 63);
        d.setColor(Color.WHITE);
        d.fillCircle((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 346, 25);
        d.setColor(darkWhite);
        d.fillCircle((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 346, 18);
        d.setColor(Color.BLACK);
        d.fillCircle((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 346, 3);
        d.drawLine((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 346,
                (int) (bottomLeft.getX() + 40 + degToPoint(deg, 25).getX()),
                (int) (bottomLeft.getY() - 346 + degToPoint(deg, 25).getY()));
        d.drawLine((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 346,
                (int) (bottomLeft.getX() + 40 + degToPoint(deg / 3, 15).getX()),
                (int) (bottomLeft.getY() - 346 + degToPoint(deg / 3, 15).getY()));

        d.setColor(brown);
        d.fillRectangle((int) bottomLeft.getX() - 4, (int) bottomLeft.getY() - 405, 88, 15);
        d.setColor(Color.DARK_GRAY);
        for (int k = 0; k < 15; k++) {
            d.drawLine((int) bottomLeft.getX() - 4 + k, (int) bottomLeft.getY() - 405 - k,
                    (int) bottomLeft.getX() + 84 - k, (int) bottomLeft.getY() - 405 - k);
        }

        d.setColor(Color.BLACK);
        for (int w = 0; w < 5; w++) {
            d.fillCircle((int) bottomLeft.getX() + 10 + 15 * w, (int) bottomLeft.getY() - 397, 3);
        }

        d.setColor(brown);
        d.fillRectangle((int) bottomLeft.getX() + 11, (int) bottomLeft.getY() - 434, 60, 15);

        d.setColor(Color.BLACK);
        for (int w = 0; w < 3; w++) {
            d.fillCircle((int) bottomLeft.getX() + 25 + 16 * w, (int) bottomLeft.getY() - 425, 3);
        }

        d.setColor(Color.DARK_GRAY);
        for (int k = 0; k < 30; k++) {
            d.drawLine((int) bottomLeft.getX() + 11 + k, (int) bottomLeft.getY() - 434 - k,
                    (int) bottomLeft.getX() + 71 - k, (int) bottomLeft.getY() - 434 - k);
        }

    }

    /**
     * This function draws the big wheel on a given draw surface.
     *
     * @param d          the draw surface
     * @param bottomLeft the point for the location
     * @param degrees        the wheel degrees
     */
    private void drawBigWheel(DrawSurface d, int degrees, Point bottomLeft) {
        d.setColor(Color.GRAY);

        d.drawLine((int) bottomLeft.getX(), (int) bottomLeft.getY(), (int) bottomLeft.getX() + 40,
                (int) bottomLeft.getY() - 130);
        d.drawLine((int) bottomLeft.getX() + 75, (int) bottomLeft.getY(), (int) bottomLeft.getX() + 40,
                (int) bottomLeft.getY() - 130);
        d.drawCircle((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 130, 120);
        d.drawCircle((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 130, 100);
        d.setColor(Color.BLACK);
        d.fillCircle((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 130, 20);

        d.setColor(Color.WHITE);
        for (int i = 0; i < 20; i++) {
            d.drawLine((int) bottomLeft.getX() + 40, (int) bottomLeft.getY() - 130,
                    (int) bottomLeft.getX() + 40 + (int) degToPoint((360 / 20) * i + degrees, 120).getX(),
                    (int) bottomLeft.getY() - 130 + (int) degToPoint((360 / 20) * i + degrees, 120).getY());
        }
    }

    /**
     * This function draws the rain drops.
     *
     * @param d    the draw surface
     * @param time the time(changes the location of the rain)
     */
    private void drawRain(DrawSurface d, int time) {

        d.setColor(Color.BLACK);
        int i = 0;
        while (i < 40) {
            d.drawLine(20 * i, time + 70, 20 * i + 1, time + 72);
            i++;
        }

    }

    /**
     * This function draws the clouds.
     *
     * @param d the draw surface
     */
    private void drawClouds(DrawSurface d) {

        for (int j = 0; j < clouds.size(); j++) {
            clouds.get(j).drawOn(d);
        }

    }

    /**
     * This function returns a direction point from an angle and length vector.
     *
     * @param deg the angle
     * @param length the length
     * @return Point the corresponding point
     */
    private Point degToPoint(int deg, int length) {
        int dx;
        int dy;
        int lineLength = length;
        dx = (int) (lineLength * Math.sin(Math.toRadians(deg)));
        dy = (int) (lineLength * Math.cos(Math.toRadians(deg)));
        Point p = new Point(dx, dy);
        return p;

    }

}
