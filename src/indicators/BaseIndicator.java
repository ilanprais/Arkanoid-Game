package indicators;

import biuoop.DrawSurface;
import interfaces.Sprite;
import maingame.GameLevel;
import other.Counter;

/**
 * The "BaseIndicator" Class; the base class for an indicator object.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public abstract class BaseIndicator implements Sprite {

    private Counter score;

    /**
     * This function adds the indicator to a game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
    }

    /**
     * This function returns the score of the level.
     *
     * @return Counter the score counter
     */
    protected Counter getScore() {
        return this.score;
    }

    /**
     * This function sets the score of the game.
     *
     * @param s the score counter
     */
    protected void setScore(Counter s) {
        this.score = s;
    }

    /**
     * This function draws the indicator on the draw surface.
     *
     * @param d the draw surface
     */
    public abstract void drawOn(DrawSurface d);

}
