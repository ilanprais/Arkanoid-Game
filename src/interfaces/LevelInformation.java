package interfaces;

import java.awt.Color;
import java.util.List;

import other.Velocity;
import sprites.Block;

/**
 * The "LevelInfrormation" interface; implemented by a level in the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public interface LevelInformation {

    /**
     * The number of balls.
     *
     * @return the amount
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     *
     * @return the speed.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     *
     * @return the width
     */
    int paddleWidth();

    /**
     * The paddle height.
     *
     * @return the height
     */
    int paddleHeight();

    /**
     * The paddle color.
     *
     * @return the color
     */
    Color paddleColor();

    /**
     * The ball radius.
     *
     * @return the radius
     */
    int ballRadius();

    /**
     * The ball color.
     *
     * @return the color
     */
    Color ballColor();

    /**
     * The ball speed.
     *
     * @return the speed
     */
    int ballSpeed();

    /**
     * The points for destroying a block.
     *
     * @return amount of points
     */
    int blockPoints();

    /**
     * The points for completing the level.
     *
     * @return amount of points
     */
    int completePoints();

    /**
     * The name of the level.
     *
     * @return the name
     */
    String levelName();

    /**
     * The background of the level.
     *
     * @return a sprite with the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and
     * location.
     *
     * @return the blocks
     */
    List<Block> blocks();

    /**
     * The number of blocks that should be removed for completing this level.
     *
     * @return the amount
     */
    int numberOfBlocksToRemove();

}