package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * The "AnimationRunner" Class; responsible for running a given animation on a
 * given gui.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     *
     * @param g   the gui
     * @param fps the frames per second
     */
    public AnimationRunner(GUI g, int fps) {
        this.gui = g;
        this.framesPerSecond = fps;
    }

    /**
     * This function will run a given animation on the gui.
     *
     * @param animation the given animation
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();

        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        return;
    }
}