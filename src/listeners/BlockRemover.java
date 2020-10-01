package listeners;

import interfaces.HitListener;
import other.Counter;
import maingame.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The "BlockRemover" Class; listens to the blocks and removes the block when
 * the block is hit.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * The constructor.
     *
     * @param game            the game
     * @param remainingBlocks amount of balls remaining in the game
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);

    }

}