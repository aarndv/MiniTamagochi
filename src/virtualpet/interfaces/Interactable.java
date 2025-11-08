package virtualpet.interfaces;

/**
 * Represents an item that can be used by the player on a Pet.
 */
public interface Interactable {
    /**
     * Execute the item's primary action on the target pet.
     * 
     * @param target The Pet on which the item will be used.
     * @throws PetIsAsleepException if the action cannot be performed because the pet is sleeping.
     * @throws InsufficientEnergyException If the pet does not have enough energy for this action (e.g., playing).
     */
    void use(Pet target) throws PetIsAsleepException, InsufficientEnergyException;
}