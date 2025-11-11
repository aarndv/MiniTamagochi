package virtualpet.models.Items;

import virtualpet.exceptions.InsufficientEnergyException;
import virtualpet.exceptions.PetIsAsleepException;
import virtualpet.models.Item;
import virtualpet.models.Pet;

public class Food extends Item {

	@Override
	public void use(Pet target) throws PetIsAsleepException, InsufficientEnergyException {
		// TODO Auto-generated method stub
		
	}

}
