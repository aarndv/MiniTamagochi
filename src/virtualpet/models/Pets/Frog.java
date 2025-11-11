package virtualpet.models.Pets;

import virtualpet.models.Pet;

/**
 * Represents a Frog pet. Provides Frog-specific ASCII art and sounds.
 */
public class Frog extends Pet {
	/**
	 * Provides the specific ASCII art for this pet in the given state.
	 * @param state The pet's current state (e.g., "sleeping").
	 * @return The ASCII art string.
	 */
	public String getASCIIDisplay(String state) {
		return null;
	}

	/**
	 * Provides the specific sound for this pet.
	 * @return The sound as a string (e.g., "Woof!").
	 */
	public String makeSound() {
		return null;
	}

	/**
	 * Provides the specific type for this pet.
	 * @return The type as a string (e.g., "Frog").
	 */
	public String getPetType() {
		return null;
	}
}
