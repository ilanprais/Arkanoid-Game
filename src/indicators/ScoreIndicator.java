package indicators;

import java.awt.Color;

import biuoop.DrawSurface;
import other.Counter;

/**
 * The "ScoreIndicator" Class; displaying the score on the screen.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class ScoreIndicator extends BaseIndicator {

    /**
     * The constructor.
     *
     * @param counter the counter to display
     */
    public ScoreIndicator(Counter counter) {
        setScore(counter);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(30, 37, "Score: " + getScore().getValue(), 18);
    }

}
