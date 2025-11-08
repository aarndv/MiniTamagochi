package virtualpet.exceptions;

/**
 * Thrown when an action is attempted on a pet that is currently asleep.
 */
@SuppressWarnings("serial")
public class PetIsAsleepException extends Exception {
    
    /**
     * Default constructor.
     */
    public PetIsAsleepException() {
        super();
    }

    /**
     * Constructor that accepts a detailed message.
     * @param message The detail message.
     */
    public PetIsAsleepException(String message) {
        super(message);
    }
}
