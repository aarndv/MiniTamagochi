package virtualpet.models.Items;

import virtualpet.interfaces.InsufficientEnergyException;
import virtualpet.interfaces.Pet;
import virtualpet.interfaces.PetIsAsleepException;
import virtualpet.models.Item;
import virtualpet.models.Pet;

public class Toy extends Item {

	@Override
	public void use(Pet target) throws PetIsAsleepException, InsufficientEnergyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(virtualpet.interfaces.Pet target) throws PetIsAsleepException, InsufficientEnergyException {
		// TODO Auto-generated method stub
		
	}

}
