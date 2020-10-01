package animations;

import java.awt.Color;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * The "PauseScreen" Class; an animation displayed when the game is paused.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(new Color(0, 191, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 95, d.getHeight() / 2 - 30, "Game Paused", 30);
        d.drawText(d.getWidth() / 2 - 130, d.getHeight() / 2, "press the space key to continue", 20);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}