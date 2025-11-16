package virtualpet.models.Pets;

import virtualpet.models.Pet;

/**
 * Represents a Dog pet. Provides Dog-specific ASCII art and sounds.
 */
public class Dog extends Pet {
	
	public Dog(String name) {
		super(name);
	}
	
	/**
	 * Provides the specific sound for this pet.
	 * @return The sound as a string (e.g., "Woof!").
	 */
	@Override
	public String makeSound() {
		return "Woof!";
	}

	/**
	 * Provides the specific type for this pet.
	 * @return The type as a string (e.g., "Frog").
	 */
	@Override
	public String getPetType() {
		return "Dog";
	}
		
	/**
	 * Provides the specific ASCII art for this pet in the given state.
	 * @param state The pet's current state (e.g., "sleeping").
	 * @return The ASCII art string.
	 */
	@Override
	public String getASCIIDisplay(String state) {
		switch(state) {
		case "sleeping":
			return
				"  .-\"-.     \n" +
				" /|- -|\\ ᶻ 𝗓 𐰁\n" +
				"{/(_0_)\\}   \n" +
				" _/ ^ \\_    \n";
		case "dead":
			return
				"  .-\"-.    \n" +
				" /|x x|\\   \n" +
				"{/(_0_)\\}  \n" +
				" _/ ^ \\_   \n";
		case "happy":
			return
				"  .-\"-.    \n" +
				" /|^ ^|\\   \n" +
				"{/(_0_)\\}  \n" +
				" _/ ^ \\_   \n";
		case "bored":
			return
				"  .-\"-.    \n" +
				" /|. .|\\   \n" +
				"{/(_0_)\\}  ...\n" +
				" _/ ^ \\_   \n";
		case "tired":
			return
				"  .-\"-.    \n" +
				" /|- O|\\   yawn..\n" +
				"{/(_0_)\\}  \n" +
				" _/ ^ \\_   \n";
		case "hungry":
			return
				"  .-\"-.    \n" +
				" /|> <|\\   \n" +
				"{/(_0_)\\}  \n" +
				" _/ ^ \\_   \n";
		case "default":
		default:
			return
				"  .-\"-.   \n" +
				" /|O O|\\   \n" +
				"{/(_0_)\\}  \n" +
				" _/ ^ \\_    \n";
		}
	}
}
