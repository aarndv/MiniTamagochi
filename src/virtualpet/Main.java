package virtualpet;

import java.util.List;
import virtualpet.exceptions.*;
import virtualpet.interfaces.*;
import virtualpet.models.*;
import java.util.Scanner;

/**
 * The main driver class for the Virtual Pet Simulator.
 */
public class Main {
    private Pet playerPet;
    private List<Interactable> inventory; 
    private Scanner scanner;
    private boolean isGameRunning;

    public static void main(String args[]) {
        return;
    }
    
    /**
     * Initializes the game. Prompts the user to choose and name a pet.
     * and populates the inventory.
     */
    void setupGame() {
        return;
    }

    /**
     * Contains the primary loop for the game. Continuously displays the pet,
     * gets user input, processes the action, and calls passTime() until
     * the game ends or the pet dies.
     */
    void runGameLoop() {
        return;
    }

    /**
     * Clears the console and renders the pet's current status, including the 
     * ASCII art from the pet (in the givne)
     * @param state
     */
    void displayPetStatus(String state) {
        return;
    }

    /**
     * Displays the main menu of actions (e.g., Feed, Play, Sleep) and
     * waits for the user to make a valid integer choice.
     * @return The integer choice made by the user.
     */
    int displayMainMenu() {
        return 0;
    }

    /**
     * Takes the user's menu choice and calls the appropriate\
     * handler method (e.g., handleFeed(), handlePlay()).
     * @param choice The integer choice from displayMainMenu().
     * @throws NumberFormatException
     */
    void processUserChoice(int choice) throws NumberFormatException {
        return;
    }

    /**
     * Handles the "Feed" action. Displays a sub-menu of available food
     * from the inventory and uses the selected food on the pet.
     */
    void handleFeed() {
        return;
    }

    /**
     * Manages the "Play" action. Displays a sub-menu of available toys
     * from the inventory and uses the selected toy on the pet.
     */
    void handlePlay() {
        return;
    }

    /**
     * A utility method to find the first item of a specific type in the invetory.
     * @param itemType The class name to search for (e.g., "Food", "Toy").
     * @return The first Interactable item found that matches the type.
     * @throws ItemNotFoundException If no item of that type exists in the inventory.
     */
    Interactable findItem(String itemType) throws ItemNotFoundException {
        return null;
    }

}
