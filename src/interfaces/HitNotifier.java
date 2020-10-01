package interfaces;

/**
 * The "HitListener" Interface; used by an object who wants to notify the
 * listeners that a block is being hit.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public interface HitNotifier {
    /**
     * This function adds hl as a listener to hit events.
     *
     * @param hl the new listener
     */
    void addHitListener(HitListener hl);

    /**
     * This function removes hl from the list of listeners to hit events.
     *
     * @param hl the listener to remove
     */
    void removeHitListener(HitListener hl);
}