package interfaces;

import geometry.Point;
import geometry.Rectangle;
import other.Velocity;
import sprites.Ball;

/**
 * The "Collidable" Interface; used by a collidable object class which
 * implements this inteface.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public interface Collidable {
    /**
     * This function Returns the "collision shape" of the object.
     *
     * @return Rectangle the rectangle of this block
     */
    Rectangle getCollisionRectangle();

    /**
     * This function Notifies the object that we collided with it at collisionPoint
     * with a given velocity. The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param hitter          the ball hitting the collidable
     * @param collisionPoint  collision point of the object with the paddle
     * @param currentVelocity current velocity of the colliding object
     * @return Velocity the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}