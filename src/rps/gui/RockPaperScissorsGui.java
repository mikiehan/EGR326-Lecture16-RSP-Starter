package rps.gui;

import rps.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class represents a GUI for a basic rock-paper-scissors game.
 * Today's version also loads the RPS game history data every time the GUI
 * loads and saves it every time you close the window.
 */
public class RockPaperScissorsGui {
	// file we use to save our serialized game state
	private static final String SAVE_FILE = "rps.dat";
	
	private JFrame frame;
	private JButton play;
	private JButton switchViews;
	private JButton history;
	private View view;
	private Game game;
	
	/**
	 * Constructs the GUI and displays it on the screen.
	 */
	public RockPaperScissorsGui() throws Exception {
		game = Game.load(SAVE_FILE);
		setupComponents();
		handleEvents();
		doLayout();
		frame.setVisible(true);
	}
	
	// sets up graphical components in the window
	private void setupComponents() {
		frame = new JFrame("Rock-Paper-Scissors");
		frame.setLocation(300, 100);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		view = new ImageView(game);
		
		play = new JButton("Play");
		switchViews = new JButton("Switch Views");
		history = new JButton("History");
	}
	
	// attaches various listeners to handle events
	private void handleEvents() {
		ActionListener listener = new ButtonListener();
		play.addActionListener(listener);
		switchViews.addActionListener(listener);
		history.addActionListener(listener);
		frame.addWindowListener(new Saver());
	}
	
	// sets up containers for layout in the window
	private void doLayout() {
		Container south = new JPanel(new FlowLayout());
		south.add(play);
		south.add(switchViews);
		south.add(history);

		frame.add(view, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
	}
	
	// switches views in (Image -> Painted -> Text) order
	private void switchViews() {
		frame.remove(view);
		
		// hey view, stop listening now.
		view.goAway();
		
		if (view instanceof TextView) {
			view = new ImageView(game);
		} else if (view instanceof ImageView) {
			view = new PaintedView(game);
		} else {
			view = new TextView(game);
		}
		
		frame.add(view, BorderLayout.CENTER);
		
		// tell Java to update the layout on the screen
		frame.validate();
	}
	
	// displays history of past games on screen as an option pane
	private void showHistory() {
		String matches = "";
		for (String match : game.getHistory()) {
			matches += match + "\n";
		}
		JOptionPane.showMessageDialog(frame, matches);
	}
	
	// This listener responds to clicks on the "Play" button.
	private class ButtonListener implements ActionListener {
		/**
		 * Called when the Play button is clicked.
		 * Plays a new game round of tic-tac-toe.
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == play) {
				game.playRound();
			} else if (event.getSource() == switchViews) {
				switchViews();
			} else {
				showHistory();
			}
		}
	}
	
	// Listens to window closing events in this GUI so that we can save
	// the game history just before the program shuts down.
	private class Saver extends WindowAdapter {
		public void windowClosing(WindowEvent event) {
			try {
				game.save(SAVE_FILE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Save failed: " + e.getMessage());
			}
		}
	}
}
