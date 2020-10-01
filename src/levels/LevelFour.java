package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import backgrounds.Jerusalem;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import other.Velocity;
import sprites.Block;
/**
 * The "levelFour" Class; the fourth level in the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class LevelFour extends BaseLevel {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public int ballRadius() {
        return 6;

    }

    @Override
    public Color ballColor() {
        return new Color(207, 181, 59);

    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(-1 * 60 + 60 * i, this.ballSpeed()));
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
        return new Color(65, 105, 225);
    }

    @Override
    public String levelName() {
        return "Level 4";
    }

    @Override
    public Sprite getBackground() {
        return new Jerusalem();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();

        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                Block addBlock;
                addBlock = new Block(new Rectangle(
                        new Point(getBorderWidth() + 5 + i * ((getWindowWidth() - getBorderWidth() * 2) / 15),
                                100 + 30 * j),
                        (getWindowWidth() - getBorderWidth() * 2) / 15, 30));

                addBlock.setColor(new Color(224, 204, 186));

                if (j == 0 && i % 2 == 0) {
                    addBlock.setColor(new Color(0, 191, 255));
                }

                blocks.add(addBlock);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

}
