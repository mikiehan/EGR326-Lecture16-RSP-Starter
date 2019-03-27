package rps.model;
import java.util.Observable;

/**
 * A RockStrategy is dumb and always chooses the rock (Weapon.ROCK).
 * @author Marty Stepp
 * @version CSE 331 Spring 2011, 5/9/2011
 */
public class RockStrategy implements RPSStrategy {
	private static final long serialVersionUID = 1L;

	/**
	 * Chooses rock for for this strategy.
	 * @return Weapon.ROCK
	 */
	public Weapon chooseWeapon() {
		return Weapon.ROCK;
	}
	
	/**
	 * Required by Observer interface; not used in this strategy.
	 */
	public void update(Observable o, Object arg) {
		
	}
}
