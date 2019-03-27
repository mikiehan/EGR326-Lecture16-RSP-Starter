package rps.gui;

import rps.model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * A text view displays the game state as buttons with text for each weapon.
 */
public class TextView extends View {
	private static final long serialVersionUID = 1L;
	private JButton move1;
	private JButton move2;
	
	/**
	 * Constructs a new text view to display data from the given game.
	 * @param game the game to display
	 * @pre game != null
	 */
	public TextView(Game game) {
		super(game);
		move1 = new JButton(" ");
		move2 = new JButton(" ");
		setState();
		this.setLayout(new FlowLayout());
		this.add(move1);
		this.add(move2);
	}

	/**
	 * Updates the state of this view based on an event that has occurred in
	 * the game model.
	 */
	public void update(Observable arg0, Object arg1) {
		setState();
	}
	
	// Updates this view's state when it is constructed or when events occur.
	private void setState() {
		// update the weapon buttons
		String weapon1 = " ";
		if (getGame().getWeapon1() != null) {
			weapon1 = getGame().getWeapon1().toString();
			move1.setText(weapon1);
		}
		
		String weapon2 = " ";
		if (getGame().getWeapon2() != null) {
			weapon2 = getGame().getWeapon2().toString();
			move2.setText(weapon2);
		}
		
		// highlight the winner
		int winner = getGame().winner();
		move1.setBackground(null);
		move2.setBackground(null);
		if (winner == 1) {
			move1.setBackground(Color.RED);
		} else if (winner == 2) {
			move2.setBackground(Color.RED);
		}
	}
}
