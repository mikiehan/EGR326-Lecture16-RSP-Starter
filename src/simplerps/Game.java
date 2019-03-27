package simplerps;

import java.util.Observable;

/**
 * A Game object represents a series of rounds of play of Rock-Paper-Scissors.
 * This class implements the Observable and Singleton design patterns.
 * Observers can be attached to be notified about changes to the observer state.
 */
public class Game extends Observable {
    private static Game instance = null;

    /**
     * Returns access to the one and only Game instance (Singleton pattern).
     * @return the sole Game instance
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // fields
    private Weapon weapon1;   // player 1's weapon in last observer round
    private Weapon weapon2;   // player 2's weapon in last observer round

    // private constructor forbids constructing additional instances
    private Game() {}

    /**
     * Returns the weapon used by player 1 in this observer.
     * @return player 1's weapon, or null if no games have been played
     */
    public Weapon getWeapon1() {
        return weapon1;
    }

    /**
     * Returns the weapon used by player 2 in this observer.
     * @return player 2's weapon, or null if no games have been played
     */
    public Weapon getWeapon2() {
        return weapon2;
    }

    /**
     * Plays a new round, drawing random weapons for both players.
     * Also notifies any observers of the change in state of this observer.
     * @modifies this
     */
    public void playRound() {
    }

    /**
     * Returns an integer representing which player won the most recent
     * round that was played.
     * @return 1 if player 1 won, 2 if player 2 won, or 0 for a tie.
     */
    public int winner() {
        if (weapon1.defeats(weapon2)) {
            return 1;
        } else if (weapon2.defeats(weapon1)) {
            return 2;
        } else {
            return 0;
        }
    }
}

