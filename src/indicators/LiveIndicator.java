package indicators;

import java.awt.Color;

import biuoop.DrawSurface;
import other.Counter;

/**
 * The "LiveIndicator" Class; displaying the amount of lives on the screen.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class LiveIndicator extends BaseIndicator {

    /**
     * The constructor.
     *
     * @param counter the counter to display
     */
    public LiveIndicator(Counter counter) {
        setScore(counter);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(140, 37, "Lives left: " + (getScore().getValue() - 1), 18);
    }

}
