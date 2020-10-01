package maingame;

import java.util.ArrayList;
import java.util.List;

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collections.GameEnvironment;
import collections.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import other.Counter;
import other.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import indicators.LevelIndicator;
import indicators.LiveIndicator;
import indicators.ScoreIndicator;

/**
 * The "GameLevel" Class; representing a level of the game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class GameLevel implements Animation {

    private int windowheight = 600;
    private int windowwidth = 800;
    private int borderwidth = 20;
    private LevelInformation lvl;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private List<Ball> balls;
    private Paddle paddle;
    private Block deathBlock;
    private Counter blockCounter;
    private Counter liveCounter;
    private String curLevel;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter ballCounter;
    private Counter scoreCounter;
    private ScoreTrackingListener scoreTracker;
    private ScoreIndicator scoreIndicator;
    private LiveIndicator liveIndicator;
    private LevelIndicator levelIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    /**
     * The constructor.
     *
     * @param lvl      the lvl
     * @param keyboard the keyboard
     * @param runner   the game runner for running the level
     * @param score    the score
     * @param lives    the number of lives counter
     * @param level    the level number counter
     */
    public GameLevel(LevelInformation lvl, KeyboardSensor keyboard, AnimationRunner runner, Counter score,
            Counter lives, Counter level) {

        this.runner = runner;
        this.keyboard = keyboard;
        this.lvl = lvl;
        this.scoreCounter = score;
        this.liveCounter = lives;
        this.curLevel = lvl.levelName();

        /*
         * Collections
         */
        sprites = new SpriteCollection();
        environment = new GameEnvironment();

        /*
         * counters
         */
        blockCounter = new Counter();
        ballCounter = new Counter();

        /*
         * balls
         */
        balls = new ArrayList<Ball>();

        /*
         * Death Block
         */
        deathBlock = new Block(new Rectangle(new Point(0, windowheight - borderwidth + 30), windowwidth, borderwidth));

        /*
         * The listeners
         */

        ballRemover = new BallRemover(this, ballCounter);
        blockRemover = new BlockRemover(this, blockCounter);
        scoreTracker = new ScoreTrackingListener(scoreCounter);

        /*
         * The background
         */
        this.addSprite(lvl.getBackground());

    }

    /**
     * This function is used to remove a collidable object from this game.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * This function is used to remove a sprite object from this game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * This function is used to add a new collidable object to this game.
     *
     * @param c the new collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * This function is used to add a new sprite object to this game.
     *
     * @param s the new sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * This function is used to Initialize a new game: create the Blocks, Balls, and
     * Paddle and add them to the game.
     */
    public void initialize() {

        buildBalls();

        buildBlocks();

        buildPaddle();

        addIndicators();

    }

    /**
     * This function adds the indicators to the level.
     */
    private void addIndicators() {
        /*
         * The score indicator based on the score tracker listener
         */
        scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);

        /*
         * The live indicator
         */
        liveIndicator = new LiveIndicator(liveCounter);
        liveIndicator.addToGame(this);

        /*
         * The level indicator
         */
        levelIndicator = new LevelIndicator(curLevel);
        levelIndicator.addToGame(this);

    }

    /**
     * This function is used to build a new Paddle and add it to the game.
     */
    private void buildPaddle() {
        paddle = new Paddle(
                new Rectangle(new Point(windowwidth / 2 - lvl.paddleWidth() / 2, windowheight - borderwidth),
                        lvl.paddleWidth(), lvl.paddleHeight()),
                keyboard, lvl.paddleColor(), lvl.paddleSpeed());

        paddle.addToGame(this);
    }

    /**
     * This function is used to build the border blocks and normal Blocks and add
     * them to the game.
     */
    private void buildBlocks() {

        List<Block> borderBlocks = new ArrayList<Block>();

        // Borders
        borderBlocks.add(new Block(new Rectangle(new Point(0, 0), borderwidth, windowwidth)));
        borderBlocks.add(new Block(new Rectangle(new Point(0, 0), windowwidth, borderwidth)));
        borderBlocks.add(new Block(new Rectangle(new Point(windowwidth - borderwidth, 0), borderwidth, windowwidth)));
        borderBlocks.add(new Block(
                new Rectangle(new Point(borderwidth, borderwidth), windowwidth - 2 * borderwidth, borderwidth)));

        for (Block b : borderBlocks) {
            b.addToGame(this);
        }

        for (Block b : lvl.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracker);
            this.blockCounter.increase(1);
        }

        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);

    }

    /**
     * This function is used to build the balls of the game, and adds them to the
     * game.
     */
    private void buildBalls() {

        for (int i = 0; i < lvl.numberOfBalls(); i++) {

            addBallAt(
                    new Point(windowwidth / 2, windowheight - 2 * borderwidth - lvl.paddleHeight() - lvl.ballRadius()),
                    lvl.initialBallVelocities().get(i));

            ballCounter.increase(1);
        }

    }

    /**
     * This function adds a new ball to the game at a given point.
     *
     * @param v the velocity for the ball
     * @param p the point to add the ball at
     */
    private void addBallAt(Point p, Velocity v) {
        Ball newB = new Ball(new Point(p.getX(), p.getY()), lvl.ballRadius(), lvl.ballColor());
        balls.add(newB);
        newB.addToGame(this);
        newB.setGameEnvironment(environment);
        newB.setVelocity(v);

    }

    /**
     * This function calls the ball remove from paddle function on all the game
     * balls.
     */
    private void removeBallsInPaddle() {
        for (Ball b : balls) {
            paddle.ballInsidePaddleRemover(b);
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        this.removeBallsInPaddle();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(), KeyboardSensor.SPACE_KEY, keyboard));
        }

        if (ballCounter.getValue() <= 0) {
            this.liveCounter.decrease(1);
            this.buildBalls();
            this.running = false;
        }

        if (blockCounter.getValue() <= 0) {
            this.scoreCounter.increase(lvl.completePoints());
            this.running = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return (!this.running);
    }

    /**
     * Number of remaining blocks in the level.
     *
     * @return Counter the block counter
     */
    public int remainingBlocks() {
        return this.blockCounter.getValue();
    }

    /**
     * This function is used to run the game, start the animation loop.
     */
    public void run() {
        this.running = true;

        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);

        return;
    }

}