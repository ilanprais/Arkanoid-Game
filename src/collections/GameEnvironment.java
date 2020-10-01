package collections;

import java.util.ArrayList;
import java.util.List;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import other.CollisionInfo;

/**
 * The "GameEnvironment" Class; holding all the Collidables in a game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class GameEnvironment {

    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * This function adds the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * This function removes the given collidable from the environment.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * This function Assumes an object moving from line.start() to line.end().the
     * function with return the closest collision point and object of the line to
     * the start of the line.
     *
     * @param trajectory the line
     * @return CollsionInfo the collision point and object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollisionObject = null;
        Point closestCollisionPoint = null;
        Point curCollision;
        /*
         * go over the collidables of the game, get for each collidable the closest
         * intersection with the trajectory and set it as the closest if necessary.
         */
        for (Collidable c : this.collidables) {
            curCollision = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (closestCollisionPoint == null) {
                closestCollisionPoint = curCollision;
                closestCollisionObject = c;
            } else if (curCollision != null
                    && curCollision.distance(trajectory.start()) < closestCollisionPoint.distance(trajectory.start())) {
                closestCollisionPoint = curCollision;
                closestCollisionObject = c;
            }
        }

        CollisionInfo ci = null;

        if (closestCollisionPoint != null) {
            ci = new CollisionInfo(closestCollisionPoint, closestCollisionObject);
        }

        return ci;
    }

}