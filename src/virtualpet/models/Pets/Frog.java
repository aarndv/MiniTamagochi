package virtualpet.models.Pets;

import virtualpet.models.Pet;

/**
 * Represents a Frog pet. Provides Frog-specific ASCII art and sounds.
 */
public class Frog extends Pet {
	
	public Frog(String name) {
		super(name);
	}
	
	/**
	 * Provides the specific sound for this pet.
	 * @return The sound as a string (e.g., "Woof!").
	 */
	@Override
	public String makeSound() {
		return "Ribbit!";
	}
	
	/**
	 * Provides the specific type for this pet.
	 * @return The type as a string (e.g., "Frog").
	 */
	@Override
	public String getPetType() {
		return "Frog";
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
				"   (-)-(-)  ᶻ 𝗓 𐰁\n" +
				"  (//'-'//)   \n" +
				" /|  > <  |\\ \n" +
				"(^^~~   ~~^^) \n";
		case "dead":
			return
				"   (x)-(x)    \n" +
				"  (//'-'//)   \n" +
				" /|  > <  |\\ \n" +
				"(^^~~   ~~^^) \n";
		case "happy":
			return
				"   (^)-(^)    \n" +
				"  (//'w'//)   ‧₊˚.\n" +
				" /|  > <  |\\ \n" +
				"(^^~~   ~~^^) \n";
		case "bored":
			return
				"   (.)-(.)    \n" +
				"  (//'-'//)   \n" +
				" /|  > <  |\\ ...\n" +
				"(^^~~   ~~^^) \n";
		case "tired":
			return
				"   (-)-(o)    \n" +
				"  (//'o'//)   yawn..\n" +
				" /|  > <  |\\ \n" +
				"(^^~~   ~~^^) \n";
		case "hungry":
			return
				"   (>)-(<)    \n" +
				"  (//'~'//)   \n" +
				" /|  > <  |\\ \n" +
				"(^^~~   ~~^^) \n";
		case "default":
		default:
			return
				"   (O)-(O)    \n" +
				"  (//'-'//)   \n" +
				" /|  > <  |\\ \n" +
				"(^^~~   ~~^^) \n";
		}
	}
}

/*
frog for safekeeping (change only to xx when dead and -- when sleep)
				"  (◐)-(◑)  \n" +
				" (   \"   )   \n" +
				"/|  >  <  |\\ \n" +
				"(~~      ~~) \n";
*/