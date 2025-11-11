package virtualpet.models.Items;

import virtualpet.exceptions.PetIsAsleepException;
import virtualpet.models.Item;
import virtualpet.models.Pet;

public class Food extends Item {
	protected int hungerValue;

	public Food(String name, String desc, int value) {
		super(name, desc);
		this.hungerValue = value;
	}

	public int getHungerValue() {
		return this.hungerValue;
	}

	/**
	 * Implements the use action for Food. Calls the pet's eat() method.
	 * @param target The pet to feed.
	 * @throws PetIsAsleepException if the pet is sleeping and cannot eat.
	 */
	@Override
	public void use(Pet target) throws PetIsAsleepException {
		target.eat(this);
	}
}
