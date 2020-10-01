package maingame;

import java.util.List;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import other.Counter;

/**
 * The "GameFlow" Class; used for running animations throughout the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter scoreCounter;
    private Counter liveCounter;
    private Counter levelCounter;

    /**
     * The contructor.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * This function is used to run a given list of levels.
     *
     * @param levels the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {

        scoreCounter = new Counter();
        liveCounter = new Counter();
        liveCounter.increase(7); // number of lives
        levelCounter = new Counter();
        levelCounter.increase(1);

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, scoreCounter,
                    liveCounter, levelCounter);

            level.initialize();

            while (liveCounter.getValue() > 0 && level.remainingBlocks() > 0) {
                level.run();
            }

            if (level.remainingBlocks() <= 0) {
                if (levelCounter.getValue() == levels.size()) {
                    // Reached the end
                    this.animationRunner.run(new KeyPressStoppableAnimation(
                            new EndScreen(scoreCounter.getValue(), true), KeyboardSensor.SPACE_KEY, keyboardSensor));
                    break;
                } else {
                    levelCounter.increase(1);
                }
            }

            if (liveCounter.getValue() <= 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(new EndScreen(scoreCounter.getValue(), false),
                        KeyboardSensor.SPACE_KEY, keyboardSensor));
                break;
            }

        }
    }
}
