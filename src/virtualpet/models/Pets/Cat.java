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
					" /\\_/\\ ,  \n" +
					"( -v- ) ) ᶻ 𝗓 𐰁\n" +
					" /   \\ /   \n" +
					"(\\|||/)    \n";
			case "dead":
				return
					" /\\_/\\ ,  \n" +
					"( x-x ) )   \n" +
					" /   \\ /   \n" +
					"(\\|||/)    \n";
			case "happy":
				return
					" /\\_/\\ ,  \n" +
					"( ^w^ ) ) ‧₊˚. \n" +
					" /   \\ /   \n" +
					"(\\|||/)    \n";
			case "bored":
				return
					" /\\_/\\ ,  \n" +
					"( O.O ) )   \n" +
					" /   \\ /  ...\n" +
					"(\\|||/)    \n";
			case "tired":
				return
					" /\\_/\\ ,  \n" +
					"( -o- ) )   yawn..\n" +
					" /   \\ /   \n" +
					"(\\|||/)    \n";
			case "hungry":
				return
					" /\\_/\\ ,  \n" +
					"( >~< ) )   \n" +
					" /   \\ /   \n" +
					"(\\|||/)    \n";
			case "default":
			default:
				return
					" /\\_/\\ ,  \n" +
					"( 'Y' ) )   \n" +
					" /   \\ /   \n" +
					"(\\|||/)    \n";				
		}
	}
}
