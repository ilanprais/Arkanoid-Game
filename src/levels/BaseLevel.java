package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import interfaces.LevelInformation;
import interfaces.Sprite;
import other.Velocity;
import sprites.Block;
/**
 * The "BaseLevel" Class; the base class for all game levels.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public abstract class BaseLevel implements LevelInformation {

    private int windowheight = 600;
    private int windowwidth = 800;
    private int borderwidth = 20;
    private int paddleheight = 15;
    private int blocklayers = 6;
    private int paddlespeed = 8;
    private int ballspeed = 5;
    private Color ballcolor = Color.BLUE;
    private Color backgroundcolor = Color.cyan;

    /**
     * The window height of the level.
     *
     * @return the height
     */
    protected int getWindowHeight() {
        return windowheight;
    }

    /**
     * The window width of the level.
     *
     * @return the width
     */
    protected int getWindowWidth() {
        return windowwidth;
    }

    /**
     * The border width of the level.
     *
     * @return the border width
     */
    protected int getBorderWidth() {
        return borderwidth;
    }

    /**
     * The amount of layers of blocks in the level.
     *
     * @return the amount
     */
    protected int getBlockLayers() {
        return blocklayers;
    }

    /**
     * The speed of the paddle.
     *
     * @return the speed
     */
    protected int getPaddleSpeed() {
        return paddlespeed;
    }

    /**
     * The color of the background.
     *
     * @return the color
     */
    protected Color getBackgroundColor() {
        return backgroundcolor;
    }

    /*
     * The default functions
     */

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public int ballRadius() {
        return 6;
    }

    @Override
    public Color ballColor() {
        return ballcolor;
    }

    @Override
    public int ballSpeed() {
        return ballspeed;
    }

    @Override
    public Color paddleColor() {
        return Color.BLUE;
    }

    @Override
    public int paddleHeight() {
        return paddleheight;
    }

    @Override
    public int blockPoints() {
        return 10;
    }

    @Override
    public int completePoints() {
        return 100;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        vel.add(new Velocity(0, 5));
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "A Level";
    }

    /*
     * The abstract functions
     */

    @Override
    public abstract Sprite getBackground();

    @Override
    public abstract List<Block> blocks();

    @Override
    public abstract int numberOfBlocksToRemove();

}
