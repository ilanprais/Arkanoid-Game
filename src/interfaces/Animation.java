package interfaces;

import biuoop.DrawSurface;

/**
 * The "Animation" interface; implemented by an animation in the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public interface Animation {
    /**
     * This function prints one frame of the animation.
     *
     * @param d the draw surface to print on
     */
    void doOneFrame(DrawSurface d);

    /**
     * This function checks if the animation should stop.
     *
     * @return boolean if it should stop
     */
    boolean shouldStop();
}
