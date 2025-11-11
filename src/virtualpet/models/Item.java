package virtualpet.models;
import virtualpet.exceptions.InsufficientEnergyException;
import virtualpet.exceptions.PetIsAsleepException;
import virtualpet.interfaces.Interactable;

/**
 * Abstract base class for all items in the game. All items are Interactable
 * and have a name and description.
 */
public abstract class Item implements Interactable {
    /** The name of the item (e.g., "Kibble"). */
    protected String itemName;
    
    /** A brief description of the item. */
    protected String description; 

    public Item(String itemName, String description) {
        this.itemName = itemName;
        this.description = description;
    }

    /**
     * Gets the item's name.
     * @return The name of the item.
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * (Abstract) Must be implemented by subclasses to define what this
     * item does when used on a pet.
     */
    public abstract void use(Pet target) throws PetIsAsleepException, InsufficientEnergyException;
}