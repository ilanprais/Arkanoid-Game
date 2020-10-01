package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.Berlin;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import other.Velocity;
import sprites.Block;
/**
 * The "levelOne" Class; the first level in the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class LevelOne extends BaseLevel {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public int ballRadius() {
        return 6;

    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        vel.add(new Velocity(0, -5));
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
    public Color paddleColor() {
        return Color.BLUE;
    }

    @Override
    public String levelName() {
        return "Level 1";
    }

    @Override
    public Sprite getBackground() {
        return new Berlin();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(new Rectangle(new Point(377, 215), 55, 20)));
        blocks.get(0).setColor(Color.BLACK);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
