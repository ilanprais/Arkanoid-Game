package listeners;

import interfaces.HitListener;
import other.Counter;
import maingame.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The "BallRemover" Class; listens to the "-1" block and removes a ball when
 * the block is hit.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * The constructor.
     *
     * @param game           the game
     * @param remainingBalls amount of balls remaining in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.game = game;

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        remainingBalls.decrease(1);
        hitter.removeFromGame(game);

    }

}
