package indicators;

import java.awt.Color;

import biuoop.DrawSurface;
import other.Counter;

/**
 * The "LevelIndicator" Class; the indicator for the current level.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class LevelIndicator extends BaseIndicator {

    /**
     * The constructor.
     *
     * @param level the counter to display
     */
    public LevelIndicator(String level) {
        setScore(stringToCounter(level));
    }

    /**
     * This function recieves a level name and returns the counter for the level.
     *
     * @param level the level string
     * @return the level counter
     */
    private Counter stringToCounter(String level) {
        Counter c = new Counter();
        c.increase(1);
        if (level.contentEquals("Level 1")) {
            c.increase(0);
        }
        if (level.contentEquals("Level 2")) {
            c.increase(1);
        }
        if (level.contentEquals("Level 3")) {
            c.increase(2);
        }
        if (level.contentEquals("Level 4")) {
            c.increase(3);
        }
        return c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (getScore().getValue() == 1) {
            d.drawText(640, 37, "Level: Berlin", 18);
        }
        if (getScore().getValue() == 2) {
            d.drawText(640, 37, "Level: Paris", 18);
        }
        if (getScore().getValue() == 3) {
            d.drawText(640, 37, "Level: London", 18);
        }
        if (getScore().getValue() == 4) {
            d.drawText(570, 37, "Level: Jerusalem of Gold", 18);
        }

    }

}
