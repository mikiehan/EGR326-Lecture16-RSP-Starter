package rps.model;
import java.io.Serializable;
import java.util.Observer;

/**
 * This interface represents strategy algorithms for players to use 
 * for playing rock-paper-scissors.
 * Note that the class now implements Serializable so Java allows it to be saved.
 * Strategies must be serializable since they are stored as fields within the
 * Game that we want to serialize.
 * @author Marty Stepp
 * @version CSE 331 Spring 2011, 5/9/2011
 */
public interface RPSStrategy extends Observer, Serializable {
	/**
	 * Instructs this strategy to choose and return its weapon in the next round
	 * of the game.
	 * @return the weapon chosen (must be rock, paper, or scissors)
	 */
	public Weapon chooseWeapon();
}
