package rps.gui;

import rps.model.Game;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * This is a superclass for all views in the RPS game.
 * It is an abstract class because it exists only to be a parent,
 * not to be directly instantiated.
 */
public abstract class View extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Game game;
	
	/**
	 * Constructs a new view to display data from the given game.
	 * @param game the game to display
	 * @pre game != null
	 */
	public View(Game game) {
		this.game = game;
		game.addObserver(this);
	}
	
	/**
	 * Returns this view's corresponding game object.
	 * @return the game being displayed
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Called by the GUI to inform the view that it is being detached from
	 * the model.
	 */
	public void goAway() {
		game.deleteObserver(this);
	}
	
	/**
	 * Each view must implement its own updating behavior to update its state
	 * whenever the state of the game changes.
	 */
	public abstract void update(Observable o, Object arg);
}
