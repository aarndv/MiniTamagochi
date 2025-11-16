package virtualpet.models.Items;

import virtualpet.exceptions.InsufficientEnergyException;
import virtualpet.exceptions.PetIsAsleepException;
import virtualpet.models.Item;
import virtualpet.models.Pet;

public class Toy extends Item {
	protected int happinessValue;

	public Toy(String name, String desc, int price, int value) {
		super(name, desc, price);
		this.happinessValue = value;
	}

	public int getHappinessValue() {
		return this.happinessValue;
	}
	
	/**
	 * Implements the use action for a Toy. Calls the pet's playWith() method.
	 * @param target The pet to play with.
	 * @throws PetIsAsleepException If the pet is sleeping.
	 * @throws InsufficientEnergyException If the pet is too tired to play.
	 */
	@Override
	public void use(Pet target) throws PetIsAsleepException, InsufficientEnergyException {
		target.playWith(this);
	}

	@Override 
	public int getEffectValue() {
		return happinessValue;
	}
}
