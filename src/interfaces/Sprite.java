package interfaces;

import biuoop.DrawSurface;

/**
 * The "Sprite" Interface; used by a sprite object class which implements this
 * inteface.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public interface Sprite {
    /**
     * This function draws the sprite to the screen.
     *
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * This function notifies the sprite that time has passed.
     */
    void timePassed();
}