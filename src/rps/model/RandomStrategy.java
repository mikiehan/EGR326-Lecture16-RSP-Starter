package rps.model;
import java.util.Observable;

/**
 * A RandomStrategy chooses its RPS move at random each time.
 * @author Marty Stepp
 * @version CSE 331 Spring 2011, 5/9/2011
 */
public class RandomStrategy implements RPSStrategy {
	private static final long serialVersionUID = 1L;

	/**
	 * Chooses the next random weapon for this strategy.
	 * @return the randomly chosen weapon
	 */
	public Weapon chooseWeapon() {
		return Weapon.random();
	}
	
	/**
	 * Required by Observer interface; not used in this strategy.
	 */
	public void update(Observable o, Object arg) {
		
	}
}
