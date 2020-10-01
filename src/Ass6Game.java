import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import animations.AnimationRunner;
import biuoop.GUI;
import interfaces.LevelInformation;
import levels.LevelFour;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import maingame.GameFlow;

//ID: 329034557

/**
 * The Main Class; creates a new game, initializes it, and runs it.
 *
 * @author Ilan Prais
 * @version 1.0
 * @since 2019-04-19
 */
public class Ass6Game {

    /**
     * The main function. The function new game, initializes it, and runs it.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Arkanoid", 800, 600);
        int fps = 60;

        List<LevelInformation> allLevels = new ArrayList<LevelInformation>();

        allLevels.add(new LevelOne());
        allLevels.add(new LevelTwo());
        allLevels.add(new LevelThree());
        allLevels.add(new LevelFour());

        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        for (int i : getIntsInRange(args)) {
            levels.add(allLevels.get(i - 1));
        }

        if (levels.size() == 0) {
            levels.addAll(allLevels);
        }

        GameFlow flow = new GameFlow(new AnimationRunner(gui, fps), gui.getKeyboardSensor());

        flow.runLevels(levels);

        gui.close();

    }

    /**
     * This function returns the ints in the range 1-4 from a array of strings.
     *
     * @param arr the string array
     * @return List the list of int values
     */
    private static List<Integer> getIntsInRange(String[] arr) {
        List<Integer> ints = new LinkedList<Integer>();

        for (String a : arr) {
            if (a.contentEquals("1") || a.contentEquals("2") || a.contentEquals("3") || a.contentEquals("4")) {
                ints.add(Integer.parseInt(a));
            }
        }
        return ints;
    }

}
