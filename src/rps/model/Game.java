package rps.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * A Game object represents a series of rounds of play of Rock-Paper-Scissors.
 * This class implements the Observable and Singleton design patterns.
 * Observers can be attached to be notified about changes to the game state.
 * Today's version has load and save methods to make it possible to save the
 * state of history of games in progress when the program starts and stops.
 * Loading and saving is done using serialization, an example of the Memento
 * design pattern.
 * Note that the class now implements Serializable so Java allows it to be saved.
 */
public class Game extends Observable implements Serializable {
	private static final long serialVersionUID = 1;

	/**
	 * Loads a game from the given file name using serialization.
	 * @return the game object loaded, or a default new game if no game exists
	 *         in the file
	 * @throws Exception if an error occurs during the serialization process
	 */
	public static Game load(String filename) {
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream obj = new ObjectInputStream(file);
			Game game = (Game) obj.readObject();
			return game;
		} catch (Exception e) {
			return new Game();
		}
	}
	
	// fields
	private Weapon weapon1;   // player 1's weapon in last game round
	private Weapon weapon2;   // player 2's weapon in last game round
	private RPSStrategy p1strat;
	private RPSStrategy p2strat;
	private int wins;
	private List<String> history;
	
	/**
	 * Constructs a new game with default random strategies.
	 */
	public Game() {
		history = new ArrayList<String>();
		setPlayer1Strategy(new RandomStrategy());
		setPlayer2Strategy(new RandomStrategy());
	}
	
	/**
	 * Returns a list of strings representing all past matches, of the form
	 * "ROCK vs. PAPER".
	 * @return list of all past matches; initially an empty list
	 */
	public List<String> getHistory() {
		return Collections.unmodifiableList(history);
	}

	/**
	 * Returns the weapon used by player 1 in this game.
	 * @return player 1's weapon, or null if no games have been played
	 */
	public Weapon getWeapon1() {
		return weapon1;
	}

	/**
	 * Returns the weapon used by player 2 in this game.
	 * @return player 2's weapon, or null if no games have been played
	 */
	public Weapon getWeapon2() {
		return weapon2;
	}
	
	/**
	 * Returns the number of games the first player has won.
	 * @return Player 1's wins (initially 0)
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Plays a new round, drawing random weapons for both players.
	 * Also notifies any observers of the change in state of this game.
	 * @modifies this 
	 */
	public void playRound() {
		weapon1 = p1strat.chooseWeapon();
		weapon2 = p2strat.chooseWeapon();
		if (winner() == 1) {
			wins++;
		}
		history.add(weapon1 + " vs. " + weapon2);
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Saves the state of this game to the given file using serialization.
	 * @param filename name of file to save to
	 * @throws Exception if any error occurs during the serialization process
	 */
	public void save(String filename) throws Exception {
		FileOutputStream file = new FileOutputStream(filename);
		ObjectOutputStream obj = new ObjectOutputStream(file);
		obj.writeObject(this);
		obj.close();
	}
	
	/**
	 * Sets player 1 to use the given strategy.
	 * @param strat the strategy to use
	 */
	public void setPlayer1Strategy(RPSStrategy strat) {
		if (p1strat != null) {
			deleteObserver(p1strat);
		}
		p1strat = strat;
		addObserver(p1strat);
	}
	
	/**
	 * Sets player 2 to use the given strategy.
	 * @param strat the strategy to use
	 */
	public void setPlayer2Strategy(RPSStrategy strat) {
		if (p2strat != null) {
			deleteObserver(p2strat);
		}
		p2strat = strat;
		addObserver(p2strat);
	}
	
	/**
	 * Returns an integer representing which player won the most recent
	 * round that was played.
	 * @return 1 if player 1 won, 2 if player 2 won, or 0 for a tie.
	 */
	public int winner() {
		if (weapon1 == weapon2) {
			return 0;
		} else if (weapon1.defeats(weapon2)) {
			return 1;
		} else {  // weapon2.defeats(weapon1)
			return 2;
		}
	}
}
