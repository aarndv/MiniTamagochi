package virtualpet.exceptions;

/**
 * Thrown when the player tries to use an item that does not exist in their inventory.
 */
@SuppressWarnings("serial")
public class ItemNotFoundException extends Exception {
    
    /**
     * Default constructor.
     */
    public ItemNotFoundException() {
        super();
    }

    /**
     * Constructor that accepts a detailed message.
     * @param message The detail message.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}