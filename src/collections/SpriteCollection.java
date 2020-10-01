package collections;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import interfaces.Sprite;

/**
 * The "SpriteCollection" Class; holding all the Sprites in a game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class SpriteCollection {

    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * This function adds a new Sprite to the list.
     *
     * @param s the new Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * This function emoves a Sprite from the list.
     *
     * @param s the Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * This function calls timePassed() on all of the Sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : new ArrayList<Sprite>(this.sprites)) {
            s.timePassed();
        }
    }

    /**
     * This function calls drawOn(d) on all of the Sprites.
     *
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * @return the list of Sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}