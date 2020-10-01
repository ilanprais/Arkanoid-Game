package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.Paris;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import other.Velocity;
import sprites.Block;
/**
 * The "levelTwo" Class; the second level in the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class LevelTwo extends BaseLevel {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public int ballRadius() {
        return 6;

    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(-1 * 50 + 10 * i, this.ballSpeed()));
        }

        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public Color paddleColor() {
        return Color.GREEN;
    }

    @Override
    public String levelName() {
        return "Level 2";
    }

    @Override
    public Sprite getBackground() {
        return new Paris();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();

        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            blocks.add(
                    new Block(
                            new Rectangle(
                                    new Point(getBorderWidth() + 5
                                            + (getWindowWidth() - 2 * getBorderWidth()) / this.numberOfBlocksToRemove()
                                                    * i,
                                            getWindowHeight() / 3),
                                    (getWindowWidth() - 2 * getBorderWidth()) / this.numberOfBlocksToRemove(), 25)));
            if (i % 3 == 0) {
                blocks.get(i).setColor(Color.BLUE);
            } else if (i % 3 == 1) {
                blocks.get(i).setColor(Color.WHITE);
            } else if (i % 3 == 2) {
                blocks.get(i).setColor(Color.RED);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 16;
    }

}
