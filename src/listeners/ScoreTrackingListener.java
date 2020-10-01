package listeners;

import interfaces.HitListener;
import other.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * The "ScoreTrackingListener" Class; listens to the blocks to track the score
 * of the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * The constructor.
     *
     * @param scoreCounter the score of the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(beingHit.getDestroyPoints());
    }
}