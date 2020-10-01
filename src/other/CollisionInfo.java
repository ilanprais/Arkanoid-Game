package other;

import geometry.Point;
import interfaces.Collidable;

/**
 * The "CollisionInfo" Class; representing a collision with the point and
 * object.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * The constructor.
     *
     * @param cp the collision point.
     * @param co the collision object.
     */
    public CollisionInfo(Point cp, Collidable co) {
        this.collisionPoint = cp;
        this.collisionObject = co;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}