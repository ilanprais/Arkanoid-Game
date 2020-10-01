package other;

/**
 * The "Counter" Class; representing a score count for a game.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */

public class Counter {

    private int count;

    /**
     * The constructor.
     */
    public Counter() {
        count = 0;
    }

    /**
     * This function adds a number to the current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * This function subtracts a number from the current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * gets current count.
     *
     * @return integer the current count
     */
    public int getValue() {
        return count;
    }
}