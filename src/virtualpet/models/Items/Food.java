package virtualpet.models.Items;

import virtualpet.exceptions.PetIsAsleepException;
import virtualpet.models.Item;
import virtualpet.models.Pet;

public class Food extends Item {
	protected int hungerValue;

	/**
	 * Implements the use action for Food. Calls the pet's eat() method.
	 * @param target The pet to feed.
	 * @throws PetIsAsleepException if the pet is sleeping and cannot eat.
	 */
	public void use(Pet target) throws PetIsAsleepException {
		return;
	}
}
