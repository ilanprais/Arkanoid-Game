package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The "KeyPressStoppableAnimation" Class; wrapping an animation, which wants to
 * be stopped when a key is pressed.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation animation;
    private String key;
    private KeyboardSensor ks;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The constructor.
     *
     * @param animation the animation to support
     * @param key       the key which will cause the animation to stop
     * @param ks        the keyboard sensor
     */
    public KeyPressStoppableAnimation(Animation animation, String key, KeyboardSensor ks) {
        this.animation = animation;
        this.key = key;
        this.ks = ks;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (ks.isPressed(key) && !isAlreadyPressed) {
            stop = true;
        }
        if (!ks.isPressed(key) && isAlreadyPressed) {
            isAlreadyPressed = false;
        }

    }

    @Override
    public boolean shouldStop() {
        if (stop) {
            return true;
        }
        return false;
    }

}
