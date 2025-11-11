package virtualpet;

import java.util.ArrayList;
import java.util.List;
import virtualpet.exceptions.*;
import virtualpet.interfaces.*;
import virtualpet.models.*;
import virtualpet.models.Items.*;
import virtualpet.models.Pets.*;
import java.util.Scanner;

/**
 * The main driver class for the Virtual Pet Simulator.
 */
public class Main {
    private Pet playerPet;
    private List<Interactable> inventory; 
    private Scanner scanner;
    private boolean isGameRunning;
    private int playerMoney;

    public static void main(String args[]) {
        Main game = new Main();
        game.setupGame();
        game.runGameLoop();
    }
    
    /**
     * Initializes the game. Prompts the user to choose and name a pet.
     * and populates the inventory.
     */
    void setupGame() {
        this.scanner = new Scanner(System.in);
        this.isGameRunning = true;
        this.inventory = new ArrayList<>();
        playerMoney = 200;

        System.out.println("Welcome to Virtual Pet Simulator!");
        System.out.println("It's time to adopt a new pet.");
        System.out.println("Choose your pet type: ");
        System.out.println("1. Cat");
        System.out.println("2. Dog");
        System.out.println("3. Frog");

        int petChoice = 0;
        while (petChoice < 1 || petChoice > 3) {
            System.out.println("Enter choice (1-3): ");

            if (scanner.hasNextInt()) {
                petChoice = scanner.nextInt();
                if (petChoice < 1 || petChoice > 3) {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3."); 
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.nextLine();

        System.out.println("What would you like to name your pet? ");
        String petName = scanner.nextLine();

        switch (petChoice) {
            case 1:
                this.playerPet = new Cat(petName);
                break;
            case 2:
                // this.playerPet = new Dog(petName);
                break;
            case 3:
                // this.playerPet = new Frog(petName);
                break;
            default:
                break;
        }

        this.inventory.add(new Food("Kibble", "Basic pet food.", 25));
        this.inventory.add(new Food("Treat", "A tasty snack.", 10));
        // TODO: Add three other foods 

        this.inventory.add(new Toy("Ball", "A simple rubber ball.", 15));
        // TODO: Add four other toys

        System.out.println("\nCongratulations! You've adopted " + playerPet.getName() + " the " + playerPet.getPetType() + "!");
        System.out.println("Press Enter to begin...");
        scanner.nextLine();
    }

    /**
     * Contains the primary loop for the game. Continuously displays the pet,
     * gets user input, processes the action, and calls passTime() until
     * the game ends or the pet dies.
     */
    void runGameLoop() {
        try {
            while (this.isGameRunning && this.playerPet.isAlive()) {
                displayPetStatus("default"); 
                int choice = displayMainMenu();
                processUserChoice(choice);

                if (this.isGameRunning) {
                    this.playerPet.passTime();
                    System.out.println("\nA moment passes...");
                    Thread.sleep(1000);
                }
            }
        } catch (PetDiedException e) {
            System.out.println("\n--- GAME OVER ---");
            System.out.println(e.getMessage());
            displayPetStatus("dead");
        } catch (InterruptedException e) {
            System.out.println("Game loop was interrupted.");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    /**
     * Clears the console and renders the pet's current status, including the 
     * ASCII art from the pet (in the givne)
     * @param state
     */
    void displayPetStatus(String state) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(this.playerPet.getASCIIDisplay(state));

        System.out.println(this.playerPet.getStatusString());
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Displays the main menu of actions (e.g., Feed, Play, Sleep) and
     * waits for the user to make a valid integer choice.
     * @return The integer choice made by the user.
     */
    int displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("[1] Feed");
        System.out.println("[2] Play");
        System.out.println("[3] Put to Sleep");
        System.out.println("[4] Put to Sleep");
        System.out.println("[5] Skip turn");
        System.out.println("[6] Exit Game");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Takes the user's menu choice and calls the appropriate\
     * handler method (e.g., handleFeed(), handlePlay()).
     * @param choice The integer choice from displayMainMenu().
     * @throws NumberFormatException
     */
    void processUserChoice(int choice) throws NumberFormatException {
        switch (choice) {
            case 1:
                handleFeed();
                break;
            case 2:
                handlePlay();
                break;
            case 3:
                playerPet.sleep();
                System.out.println(playerPet.getName() + " is now sleeping.");
                break;
            case 4:
                handleShop();
                break;
            case 5:
                break;
            case 6:
                this.isGameRunning = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Handles the "Feed" action. Displays a sub-menu of available food
     * from the inventory and uses the selected food on the pet.
     */
    void handleFeed() {
        System.out.println("What food will you give?");

        List<Food> foodItems = new ArrayList<>();
        for (Interactable item : this.inventory) {
            if (item instanceof Food) {
                foodItems.add((Food) item);
            }
        }

        if (foodItems.isEmpty()) {
            System.out.println("You have no food!");
            return;
        }

        for (int i = 0; i < foodItems.size(); i++) {
            System.out.println((i + 1) + ". " + foodItems.get(i).getItemName());
        }

        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice < 1 || choice > foodItems.size()) {
            System.out.println("Invalid choice.");;
            return;
        }

        Food chosenFood = foodItems.get(choice - 1);

        try {
            chosenFood.use(this.playerPet);
            System.out.println(playerPet.getName() + " ate the " + chosenFood.getItemName() + ".");
            inventory.remove(chosenFood);
        } catch (PetIsAsleepException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Manages the "Play" action. Displays a sub-menu of available toys
     * from the inventory and uses the selected toy on the pet.
     */
    void handlePlay() {
        System.out.println("Not implemented yet.");
        // TODO: 
    }

    /**
     * Manages the "Shop" action. Displays a sub-menu of available 
     * toys and food and appends it to the inventory if bought.
     * Subtracting the necessary amount of money from the player's 
     * money.
     */
    void handleShop() {
        System.out.println("Not implemented yet.");
    }
