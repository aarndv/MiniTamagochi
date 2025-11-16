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
    private List<Food> availableFoods = new ArrayList<>();
    private List<Toy> availableToys = new ArrayList<>();
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

        availableFoods.add(new Food("Kibble", "Basic pet food.", 10, 25));
        availableFoods.add(new Food("Treat", "A tasty snack.", 5, 10));
        availableFoods.add(new Food("Canned Food", "Millenium Tuna in a can.", 20, 40));
        availableFoods.add(new Food("Fish", "A fish caught by Jax", 30, 50));
        availableFoods.add(new Food("Bugs", "Tasty for amphibians.", 10, 30));
        availableToys.add(new Toy("Ball", "A simple rubber ball.", 15, 15));
        availableToys.add(new Toy("Bone", "A sturdy bone.", 20, 25));
        availableToys.add(new Toy("Rope", "Ideal for tug of war.", 25, 35));
        availableToys.add(new Toy("Plushy", "Adorable toy for", 15, 20));
        availableToys.add(new Toy("Laser Pointer", "Distract them with a red light.", 20, 30));
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
                this.playerPet = new Dog(petName);
                break;
            case 3:
                this.playerPet = new Frog(petName);
                break;
            default:
                break;
        }

        this.inventory.add(new Food("Kibble", "Basic pet food.", 10, 25));
        this.inventory.add(new Food("Treat", "A tasty snack.", 5, 10));
        this.inventory.add(new Toy("Ball", "A simple rubber ball.", 15, 15));

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
                if (this.playerPet.isTired()) {
                    displayPetStatus("tired");
                } else if (this.playerPet.isHungry()) {
                    displayPetStatus("hungry");
                } else if (this.playerPet.isBored()) {
                    displayPetStatus("bored");
                } else {
                    displayPetStatus("default"); 
                } 
                
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
        if (state == "default") 
        	System.out.println('\n' + this.playerPet.makeSound());
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
        System.out.println("[4] Shop");
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
        int exitChoice=0;
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
            Food food = foodItems.get(i);
            System.out.println((i + 1) + ". " + food.getItemName() + 
                " - " + food.getItemDescription() + " (+" + food.getEffectValue() +
                " happiness)"
            );
            exitChoice++;
        }
        System.out.println((exitChoice + 1) + ". Exit");

        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice < 1 || choice > foodItems.size() + 1) {
            System.out.println("Invalid choice.");
            return;
        }

        if (choice == foodItems.size() + 1) {
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
        int exitChoice=0;
        final int ENERGY_COST = 10;
        System.out.println("What toy will you play with?");

        List<Toy> toyItems = new ArrayList<>();
        for (Interactable item : this.inventory) {
            if (item instanceof Toy) {
                toyItems.add( (Toy) item);
            }
        }

        if (toyItems.isEmpty()) {
            System.out.println("You have no toys!");
            return;
        }

        for (int i = 0; i < toyItems.size(); i++) {
            Toy toy = toyItems.get(i); 
            System.out.println((i + 1) + ". " + toy.getItemName() + 
                " - " + toy.getItemDescription() + " (+" + toy.getEffectValue() +
                " happiness, -" + ENERGY_COST + " energy)"
            );
            exitChoice++;
        }
        System.out.println((exitChoice + 1) + ". Exit");
        
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > toyItems.size() + 1) {
            System.out.println("Invalid choice.");
            return;
        }

        if (choice == toyItems.size() + 1) {
            return;
        }

        Toy chosenToy = toyItems.get(choice - 1);

        try {
            chosenToy.use(this.playerPet); 
            System.out.println(playerPet.getName() + " played with the " + chosenToy.getItemName() + "." );
            inventory.remove(chosenToy);
        } catch (PetIsAsleepException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientEnergyException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Manages the "Shop" action. Displays a sub-menu of available 
     * toys and food and appends it to the inventory if bought.
     * Subtracting the necessary amount of money from the player's 
     * money.
     */
    void handleShop() {
        boolean isShopping = true;

        while (isShopping) {
            int categoryChoice = 0;
            System.out.println("\n--- Welcome to the Pet Shop ---");
            System.out.println("You have: Php " + this.playerMoney);
            System.out.println("---------------------------------");
            System.out.println("What would you like to buy?");
            System.out.println("[1] - Food");
            System.out.println("[2] - Toys");
            System.out.println("[3] - Back to main menu");
            System.out.println("Enter choice: ");

            if (scanner.hasNextInt()) {
                categoryChoice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch (categoryChoice) {
                case 1:
                    System.out.println("\n--- Food Items ---");
                    for (int i = 0; i < availableFoods.size(); i++) {
                        Food food = availableFoods.get(i);
                        System.out.println("[" + (i + 1) + "] " + food.getItemName()
                            + " - " + food.getItemDescription() + " - Php "
                            + food.getPrice()
                        );
                    }
                    System.out.println("[" + (availableFoods.size() + 1) + "] Back to shop menu");

                    System.out.println("Enter choice: ");
                    int foodChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (foodChoice > 0 && foodChoice <= availableFoods.size()) {
                        int itemIndex = foodChoice - 1;
                        Food selectedFood = availableFoods.get(itemIndex);
                        int price = selectedFood.getPrice(); 

                        if (this.playerMoney >= price) {
                            this.playerMoney -= price;
                            this.inventory.add(new Food(
                                selectedFood.getItemName(),
                                selectedFood.getItemDescription(),
                                selectedFood.getPrice(),
                                selectedFood.getHungerValue()
                            ));

                            availableFoods.remove(itemIndex);
                            System.out.println("You bought " + selectedFood.getItemName() + " for Php " + price + "!");
                        } else {
                            System.out.println("Insufficient Balance.");
                        }
                    } else if (foodChoice == availableFoods.size() + 1) {
                        break;           
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Toy Items ---");
                    for (int i = 0; i < availableToys.size(); i++) {
                        Toy toy = availableToys.get(i);
                        System.out.println("[" + (i + 1) + "]" + toy.getItemName()
                            + " - " + toy.getItemDescription() + " - Php "
                            + toy.getPrice()
                        );
                    }
                    System.out.println("[" + (availableToys.size() + 1) + "] Back to shop menu");

                    System.out.println("Enter choice: ");
                    int toyChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (toyChoice > 0 && toyChoice <= availableToys.size()) {
                        int itemIndex = toyChoice - 1;
                        Toy selectedToy = availableToys.get(itemIndex);
                        int price = selectedToy.getPrice();

                        if (this.playerMoney >= price) {
                            this.playerMoney -= price;

                            this.inventory.add(new Toy(
                                selectedToy.getItemName(), 
                                selectedToy.getItemDescription(), 
                                selectedToy.getPrice(), 
                                selectedToy.getHappinessValue()
                            ));
                            availableToys.remove(itemIndex);
                            System.out.println("You bought " + selectedToy.getItemName() + " for Php " + price + "!");
                        } else {
                            System.out.println("Insufficient Balance.");
                        }
                    } else if (toyChoice == availableToys.size() + 1) {
                        break;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    isShopping = false;
                    System.out.println("Leaving the shop...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (categoryChoice != 3) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }
}