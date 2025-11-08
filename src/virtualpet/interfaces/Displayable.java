package virtualpet.interfaces;

/**
 * Represents an object that has a visual representation in the console.
 */
public interface Displayable {
    /**
     * Gets a summary of the object's current state (e.g., state).
     * 
     * @return A multi-line art containing the status.
     */
    public String getStatusString();
    /**
     * Gets a multi-line ASCII art string representing the object in a specific state.
     * @param state The state to display (e.g., "happy", "sleeping", "dead").
     * @return A multi-line string containing the ASCII art.
     */
    public String getASCIIDisplay(String state);
}