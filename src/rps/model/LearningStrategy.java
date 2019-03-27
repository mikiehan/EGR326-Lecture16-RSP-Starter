package rps.model;
import java.util.Observable;

/**
 * A LearningStrategy looks at the result of the last game and picks the
 * weapon that would beat the other player's last weapon.
 * @author Marty Stepp
 * @version CSE 331 Spring 2011, 5/9/2011
 */
public class LearningStrategy implements RPSStrategy {
	private static final long serialVersionUID = 1L;

	private Weapon opponentsLastMove;
	private int playerNumber;
	
	/**
	 * Constructs a learning strategy for the given player.
	 * @param playerNumber number of player to use (1 or 2)
	 * @pre 1 <= playerNumber <= 2
	 */
	public LearningStrategy(int playerNumber) {
		opponentsLastMove = null;
		this.playerNumber = playerNumber;
	}
	
	/**
	 * Chooses the weapon for this learning strategy.
	 * If this is the first game, chooses randomly;
	 * otherwise, chooses the weapon to beat the opponent's weapon
	 * from the last game.
	 */
	public Weapon chooseWeapon() {
		if (opponentsLastMove == null) {
			return Weapon.random();
		} else if (opponentsLastMove == Weapon.ROCK) {
			return Weapon.PAPER;
		} else if (opponentsLastMove == Weapon.PAPER) {
			return Weapon.SCISSORS;
		} else {
			return Weapon.ROCK;
		}
	}
	
	/**
	 * Called by the observable game when a round is played.
	 * The learning strategy responds to this notification by examining
	 * the opponent's last move and remembering it for later.
	 */
	public void update(Observable o, Object arg) {
		Game game = (Game) o;
		if (playerNumber == 1) {
			opponentsLastMove = game.getWeapon2();
		} else {
			opponentsLastMove = game.getWeapon1();
		}
	}
}








