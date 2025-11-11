package virtualpet.models.Pets;

import virtualpet.models.Pet;

/**
 * Represents a Cat pet. Provides Cat-specific ASCII art and sounds.
 */
public class Cat extends Pet {

	public Cat(String name) {
		super(name);
	}

	/**
	 * Provides the specific sound for this pet.
	 * @return The sound as a string (e.g., "Woof!").
	 */
	@Override
	public String makeSound() {
		return "Meow!";
	}

	/**
	 * Provides the specific type for this pet.  * @return The type as a string (e.g., "Frog").  */
	@Override
	public String getPetType() {
		return "Cat";
	}

	/**
	 * Provides the specific ASCII art for this pet in the given state.
	 * @param state The pet's current state (e.g., "sleeping").
	 * @return The ASCII art string.
	 */
	@Override
	public String getASCIIDisplay(String state) {
		switch (state) {
			case "sleeping":
				return
					"/\\.../\\   \n" +
					"(  - . - )   \n" +
					"(   _   ) zZz\n" +
					"(_______)    \n";
			case "dead":
				return
					"/\\.../\\    \n" +
					"(  X . X ) rip \n" +
					"(<  --  >)    \n" +
					"(________)    \n";
			case "happy":
				return
					" /\\.../\\    \n" +
					"(  ^ . ^ )    \n" +
					"(  \\w/   )    \n" +
					"(________)    \n";
			case "default":
			default:
				return
					" /\\.../\\    \n" +
					"(  ^ . ^ )    \n" +
					"(    _   )    \n" +
					"(________)    \n";				
		}
	}
}
