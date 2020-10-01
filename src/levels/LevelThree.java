package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.London;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import other.Velocity;
import sprites.Block;
/**
 * The "leveThree" Class; the third level in the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class LevelThree extends BaseLevel {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public int ballRadius() {
        return 6;

    }

    @Override
    public Color ballColor() {
        return Color.RED;

    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(-1 * 40 + 80 * i, this.ballSpeed()));
        }

        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public Color paddleColor() {
        return new Color(222, 184, 135);
    }

    @Override
    public String levelName() {
        return "Level 3";
    }

    @Override
    public Sprite getBackground() {
        return new London();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6 + j; i++) {
                Block addBlock;
                addBlock = new Block(new Rectangle(
                        new Point((getWindowWidth() - getBorderWidth() - 60) - i * 60, 250 - j * 30), 60, 30));
                Color c;
                if (i == 2 || j == 2) {
                    c = Color.RED;
                } else {
                    c = Color.WHITE;
                }
                addBlock.setColor(c);

                blocks.add(addBlock);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

}
