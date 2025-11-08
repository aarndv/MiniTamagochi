package virtualpet.exceptions;

/**
 * Thrown when the pet's stats degrade to a point where it can no longer live.
 */
@SuppressWarnings("serial")
public class PetDiedException extends Exception {
    
    /**
     * Default constructor.
     */
    public PetDiedException() {
        super();
    }

    /**
     * Constructor that accepts a detailed message.
     * @param message The detail message.
     */
    public PetDiedException(String message) {
        super(message);
    }
}