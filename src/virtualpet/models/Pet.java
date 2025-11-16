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
    
    /** The pet's current hunger level. 0 is starving. */
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

    public Pet(String name) {
        this.name = name;
        this.age = 0;
        this.isAlive = true;
        this.isAsleep = false;

        this.maxHunger = 100;
        this.hunger = 85; 
        this.maxEnergy = 100;
        this.energy = 100;
        this.maxHappiness = 100;
        this.happiness = 80;
        
    }

    /**
     * Simulates one "tick" of time passing. This will degrade the pet's
     * hunger, happiness, and energy, and check if the pet has died. 
     * The energy level won't degrade if the pet is asleep. Meanwhile,
     * the happiness level will degrade more slowly if the pet is asleep.
     * 
     * @throws PetDiedException If this time tick causes a stat to drop to a fatal level.
     */
    public void passTime() throws PetDiedException {
        if (!this.isAlive) return;

        this.age++;
        this.hunger -= 5;
        this.happiness -= 3;

        if (this.isAsleep) {
            this.energy = Math.min(this.maxEnergy, this.energy + 20); 
            if (this.energy == this.maxEnergy) {
                this.wakeUp();
            }
        } else {
            this.energy = Math.max(0, this.energy - 5);
            if (this.energy == 0) {
                this.sleep();
            }
        }

        if (this.hunger <= 0) {
            this.isAlive = false;
            throw new PetDiedException(this.name + " has starved...");
        }
        if (this.happiness <= 0) {
            this.isAlive = false;
            throw new PetDiedException(this.name + " died of loneliness...");
        }
    } 

    /**
     * Increases the pet's hunger level based on the food's value.
     * Prevents eating if the pet is asleep.
     * 
     * @param food The food item to be consumed.
     * @throws PetIsAsleepException If the pet is sleeping.
     */
    public void eat(Food food) throws PetIsAsleepException {
        if (this.isAsleep) {
            throw new PetIsAsleepException(this.name + " is asleep and cannot eat.");
        }
        this.hunger = Math.min(this.maxHunger, this.hunger + food.getHungerValue());
    }

    /**
     * Increases the pet's happiness and decreases its energy.
     * Prevents playing if the pet is asleep or insufficient energy.
     *
     * @param toy The Toy item to be played with.
     * @throws PetIsAsleepException If the pet is sleeping.
     * @throws InsufficientEnergyException If the pet is too tired to play.
     */
    public void playWith(Toy toy) throws PetIsAsleepException, InsufficientEnergyException {
        if (this.isAsleep) {
            throw new PetIsAsleepException(this.name + " is asleep and cannot play.");
        } 
        if (this.energy < 10) { 
            throw new InsufficientEnergyException(this.name + " is too tired to play.");
        }
        this.energy -= 10;
        this.happiness = Math.min(this.maxHappiness, this.happiness + toy.getHappinessValue())
    }
    
    /**
     * Sets the pet's state to asleep, allowing energy to regenerate via passTime()
     */
    public void sleep() {
        this.isAsleep = true;
    }

    /**
     * Sets the pet's state to awake.
     */
    public void wakeUp() {
        this.isAsleep = false;
    }

    /**
     * Getters
     */
    public boolean isHungry() { return this.hunger <= this.maxHunger * 0.50; } 
    public boolean isTired() { return this.energy <= this.maxEnergy * 0.40; }
    public boolean isBored() { return this.happiness <= this.maxHappiness * 0.50; }
    public boolean isAlive() { return this.isAlive; }
    public String getName() { return this.name; }

    /**
     * Generates a formatted string of the pet's core stats (e.g., Hunger: 5/10).
     * @return The pet's status as a single line of text.
     */
    public String getStatusString() {
        String status = this.isAsleep ? "Sleeping" : "Awake";
        return String.format(
            "[%s] | Hunger: %d/%d | Happiness: %d/%d | Energy: %d/%d | Status %s",
            this.name, this.hunger, this.maxHunger,
            this.happiness, this.maxHappiness,
            this.energy, this.maxEnergy, status
        );
    }

    /**
     * (Abstract) Must be implemented by subclasses to provide pet-specific ASCII art.
     * @param state The state (e.g., "happy") to display.
     * @return The ASCII art string.
     */
    @Override
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
