package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import maingame.GameLevel;
import other.Velocity;

/**
 * The "Paddle" Class; representing a paddle with a rectangle, controlling
 * keyboard, and color.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;
    private int speed;

    /**
     * The constructor.
     *
     * @param shape    the rectangle shape of the paddle
     * @param keyboard the keyboard controlling the paddle
     * @param color    the color of the paddle
     * @param speed    the speed of the paddle
     */
    public Paddle(Rectangle shape, biuoop.KeyboardSensor keyboard, Color color, int speed) {
        this.shape = shape;
        this.keyboard = keyboard;
        this.color = color;
        this.speed = speed;
    }

    /**
     * This function moves the paddle right.
     */
    public void moveRight() {
        /*
         * check if the paddle reached the end of the screen, if so then block it from
         * moving right over the border
         */
        if (this.shape.getUpperLeft().getX() + this.shape.getWidth() <= 800 - 20 - this.speed) {
            this.shape.setUpperLeft(new Point(shape.getUpperLeft().getX() + 8, shape.getUpperLeft().getY()));
        } else {
            this.shape.setUpperLeft(new Point(800 - 20 - 2 - this.shape.getWidth(), shape.getUpperLeft().getY()));
        }
    }

    /**
     * This function moves the paddle left.
     */
    public void moveLeft() {
        /*
         * check if the paddle reached the end of the screen, if so then block it from
         * moving left over the border
         */
        if (this.shape.getUpperLeft().getX() >= 20 + this.speed) {
            this.shape.setUpperLeft(new Point(shape.getUpperLeft().getX() - this.speed, shape.getUpperLeft().getY()));
        } else {
            this.shape.setUpperLeft(new Point(20 + 2, shape.getUpperLeft().getY()));
        }
    }

    @Override
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) (this.getCollisionRectangle().getUpperLeft().getX());
        int y = (int) (this.getCollisionRectangle().getUpperLeft().getY());
        int width = (int) this.getCollisionRectangle().getWidth();
        int height = (int) this.getCollisionRectangle().getHeight();
        surface.fillRectangle(x, y, width, height);

        surface.setColor(Color.WHITE);
        surface.drawRectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = currentVelocity;
        Line[] sides = this.shape.sides();

        if (sides[0].isIntersecting(new Line(collisionPoint, collisionPoint))
                || sides[1].isIntersecting(new Line(collisionPoint, collisionPoint))) {

            int region = inRegion(collisionPoint.getX(), this.getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getUpperLeft().getX());

            if (region == 1) {
                newV = Velocity.fromAngleAndSpeed(300,
                        Math.sqrt(newV.getDx() * newV.getDx() + newV.getDy() * newV.getDy()));
            }

            if (region == 2) {
                newV = Velocity.fromAngleAndSpeed(330,
                        Math.sqrt(newV.getDx() * newV.getDx() + newV.getDy() * newV.getDy()));
            }

            if (region == 3) {
                newV = new Velocity(newV.getDx(), -1 * newV.getDy());
            }

            if (region == 4) {
                newV = Velocity.fromAngleAndSpeed(30,
                        Math.sqrt(newV.getDx() * newV.getDx() + newV.getDy() * newV.getDy()));
            }

            if (region == 5) {
                newV = Velocity.fromAngleAndSpeed(60,
                        Math.sqrt(newV.getDx() * newV.getDx() + newV.getDy() * newV.getDy()));
            }

        }

        if (sides[2].isIntersecting(new Line(collisionPoint, collisionPoint))
                || sides[3].isIntersecting(new Line(collisionPoint, collisionPoint))) {
            newV = new Velocity(-1 * newV.getDx(), newV.getDy());
        }

        return newV;
    }

    /**
     * This function removes the ball is its stuck inside the paddle.
     *
     * @param ball the ball to remove
     */
    public void ballInsidePaddleRemover(Ball ball) {
        if (ball.getY() > this.shape.getUpperLeft().getY()
                && ball.getY() < this.shape.getUpperLeft().getY() + this.shape.getHeight()) {
            if (ball.getX() > this.shape.getUpperLeft().getX()
                    && ball.getX() < this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2) {
                ball.setCenter(new Point(
                        this.shape.getUpperLeft().getX() - 2 * ball.getSize() - ball.getVelocity().getDx() - this.speed,
                        ball.getY()));
                ball.setVelocity(ball.getVelocity().getDx(), ball.getVelocity().getDy());
            }
            if (ball.getX() >= this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2
                    && ball.getX() < this.shape.getUpperLeft().getX() + this.shape.getWidth()) {
                ball.setCenter(new Point(this.shape.getUpperLeft().getX() + this.shape.getWidth() + 2 * ball.getSize()
                        + ball.getVelocity().getDx() + this.speed, ball.getY()));
                ball.setVelocity(ball.getVelocity().getDx(), ball.getVelocity().getDy());
            }

        }

    }

    /**
     * This function calculates the region of a x coordinate in a paddle.
     *
     * @param x     coordinate of the point
     * @param width rectangle width
     * @param rectX x coordinate of left of the paddle
     * @return integer the region
     */
    public int inRegion(double x, double width, double rectX) {
        double limit = rectX;
        while (limit + width / 5 <= rectX + width) {
            if (x >= limit && x < limit + width / 5) {
                return (int) ((limit - rectX) / (width / 5) + 1);
            }
            limit += width / 5;
        }
        return -1;
    }

    /**
     * This function adds this paddle to a game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}