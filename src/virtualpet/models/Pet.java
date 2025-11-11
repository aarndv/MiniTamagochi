package virtualpet.models;

import virtualpet.exceptions.InsufficientEnergyException;
import virtualpet.exceptions.PetDiedException;
import virtualpet.exceptions.PetIsAsleepException;
import virtualpet.interfaces.Displayable;
import virtualpet.models.Items.Food;
import virtualpet.models.Items.Toy;

/**
 * Abstract base class for all pets. Manages core stats, state (asleep, alive),
 * and basic actions like eating, and playing. Implements Displayable
 * to be rendered on screen.
 */
public abstract class Pet implements Displayable {
    /** The pet's Given name */
    protected String name;
    
    /** The pet's current hunger leve. 0 is starving. */
    protected int hunger;

    /** The maximum hunger level. */
    protected int maxHunger;

    /** The pet's current happiness level. 0 is unhappy. */
    protected int happiness; 
    
    /** The maximum happiness level. */
    protected int maxHappiness;

    /** The pet's current energy level. 0 is exhausted. */
    protected int energy;

    /** The maximum energy level. */
    protected int maxEnergy;

    /** The pet's age, typically incremented with passTime() */
    protected int age;

    /** Flag indicating if the pet is currently alive. */
    protected boolean isAlive;
    
    /** Flag indicating if the pet is currently sleeping. */
    protected boolean isAsleep;

    /**
     * Simulates one "tick" of time passing. This will degrade the pet's
     * hunger, happiness, and energy, and check if the pet has died. 
     * The energy level won't degrade if the pet is asleep. Meanwhile,
     * the happiness level will degrade more slowly if the pet is asleep.
     * 
     * @throws PetDiedException If this time tick causes a stat to drop to a fatal level.
     */
    void passTime() throws PetDiedException {} 

    /**
     * Increases the pet's hunger level based on the food's value.
     * Prevents eating if the pet is asleep.
     * 
     * @param food The food item to be consumed.
     * @throws PetIsAsleepException If the pet is sleeping.
     */
    void eat(Food food) throws PetIsAsleepException {}

    /**
     * Increases the pet's happiness and decreases its energy.
     * Prevents playing if the pet is asleep or insufficient energy.
     *
     * @param toy The Toy item to be played with.
     * @throws PetIsAsleepException If the pet is sleeping.
     * @throws InsufficientEnergyException If the pet is too tired to play.
     */
    void playWith(Toy toy) throws PetIsAsleepException, InsufficientEnergyException {}
    
    /**
     * Sets the pet's state to asleep, allowing energy to regenerate via passTime()
     */
    void sleep() {}

    /**
     * Sets the pet's state to awake.
     */
    void wakeUp() {}

    /**
     * Generates a formatted string of the pet's core stats (e.g., Hunger: 5/10).
     * @return The pet's status as a single line of text.
     */
    public String getStatusString() {
        return null;
    }

    /**
     * (Abstract) Must be implemented by subclasses to provide pet-specific ASCII art.
     * @param state The state (e.g., "happy") to display.
     * @return The ASCII art string.
     */
    public abstract String getASCIIDisplay(String state);

    /**
     * (Abstract) Must be implemented by subclasses to provide a pet-specific sound.
     * @return The pet's sound as a string (e.g., "Meow!").
     */
    public abstract String makeSound();

    /**
     * (Abstract) Must be implemented by subclasses to provide the pet type.
     * @return The pet type as a string (e.g., "Cat").
     */
    public abstract String getPetType();
}
