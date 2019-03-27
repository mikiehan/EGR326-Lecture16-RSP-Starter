package rps.gui;

import rps.model.Game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Observable;

/**
 * An image view displays the game state as buttons with image icons for each
 * weapon.
 */
public class ImageView extends View {
	private static final long serialVersionUID = 1L;
	private JButton move1;
	private JButton move2;
	
	/**
	 * Constructs a new image view to display data from the given game.
	 * @param game the game to display
	 * @pre game != null
	 */
	public ImageView(Game game) {
		super(game);
		move1 = new JButton(getIcon("blank.png"));
		move2 = new JButton(getIcon("blank.png"));
		setState();
		this.setLayout(new FlowLayout());
		// this.setLayout(new CascadingLayout());
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
		String file1 = "blank.png";
		String file2 = "blank.png";
		if (getGame().getWeapon1() != null) {
			file1 = getGame().getWeapon1().toString().toLowerCase() + ".png";
		}
		if (getGame().getWeapon2() != null) {
			file2 = getGame().getWeapon2().toString().toLowerCase() + ".png";
		}
		move1.setIcon(getIcon(file1));
		move2.setIcon(getIcon(file2));
		
		// highlight the winner
		int winner = getGame().winner();
		move1.setBackground(null);
		move2.setBackground(null);
		if (winner == 1) {
			move1.setBackground(Color.YELLOW);
		} else if (winner == 2) {
			move2.setBackground(Color.YELLOW);
		}
	}
	
	private ImageIcon getIcon(String filename) {
		URL url = RockPaperScissorsGui.class.getResource(filename);
		System.out.println(url);
		if (url == null) {
			System.out.println("using file");
			return new ImageIcon(filename);
		} else {
			System.out.println("using URL / JAR");
			return new ImageIcon(url);
		}
	}
}











