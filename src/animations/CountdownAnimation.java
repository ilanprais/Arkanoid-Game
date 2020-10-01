package animations;

import java.awt.Color;

import biuoop.DrawSurface;
import collections.SpriteCollection;
import interfaces.Animation;

/**
 * The "CountdownAnimation" Class; an animation displaying a countdown from 3 to
 * 1..
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */

public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private int count;
    private SpriteCollection gameScreen;
    private long waitTime;

    /**
     * The constructor.
     *
     * @param numOfSeconds how long the animation should be
     * @param countFrom    the number to start the countdown from
     * @param gameScreen   the sprite collection to be the background of the
     *                     countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.waitTime = System.currentTimeMillis();
        this.count = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        gameScreen.drawAllOn(d);
        d.setColor(Color.DARK_GRAY);
        d.drawText(d.getWidth() / 2 - 10, (int) (d.getHeight() / 1.5), count + "", 50);

        countUpdator();

    }

    @Override
    public boolean shouldStop() {
        if (count <= 0) {
            return true;
        }
        return false;
    }

    /**
     * This function updates the count when necessary.
     */
    private void countUpdator() {

        if (System.currentTimeMillis() - waitTime >= (long) ((numOfSeconds / countFrom) * 1000)) {
            count -= 1;
            waitTime = System.currentTimeMillis();
        }

    }

}