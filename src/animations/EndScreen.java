package animations;

import java.awt.Color;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * The "EndScreen" Class; an animation displaying the screen at the end with the
 * score.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class EndScreen implements Animation {

    private int score;
    private boolean won;

    /**
     * The constructor.
     *
     * @param score the score to display
     * @param won   has the player won
     */
    public EndScreen(int score, boolean won) {
        this.score = score;
        this.won = won;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0, 191, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        if (won) {
            d.drawText(200, 300, "You Win! Your score is " + score, 30);
        } else {
            d.drawText(200, 300, "Game Over. Your score is " + score, 30);
        }

    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}
