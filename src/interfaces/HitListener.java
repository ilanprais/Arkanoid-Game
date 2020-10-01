package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * The "HitListener" Interface; used by an object who wants to "listen" to
 * blocks being hit.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}