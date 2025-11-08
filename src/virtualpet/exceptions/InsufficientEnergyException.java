package virtualpet.exceptions;

/**
 * Thrown when a pet does not have enough energy to perform a requested action.
 */
@SuppressWarnings("serial")
public class InsufficientEnergyException extends Exception {
    
    /**
     * Default constructor.
     */
    public InsufficientEnergyException() {
        super();
    }

    /**
     * Constructor that accepts a detailed message.
     * @param message The detail message.
     */
    public InsufficientEnergyException(String message) {
        super(message);
    }
}