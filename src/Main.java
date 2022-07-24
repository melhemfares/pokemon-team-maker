/**
 * Melhem Fares
 * 06/09/2022
 * Pokemon Team Maker
 * Create a team of up to 6 Pokemon each with 4 move slots
 */

/*
NOTE FOR USER:

It is best to run this program with the terminal window as large as possible to see all the content.
 */

import java.util.*;
import java.lang.*;

public class Main {

    //Scanner
    static Scanner input = new Scanner(System.in);

    //Arrays
    static Type[] types = Type.values();
    static Effect[] effects = Effect.values();
    static Pokemon[] team = new Pokemon[6];

    //Default settings
    static int numOfPokemon = 0;

    //Placeholder variable values until the user gives input
    static String name = "";
    static int level = 0;
    static int health = 0;
    static char gender = ' ';
    static int tempType;
    static int power = 0;
    static int accuracy = 0;
    static Type type = null;
    static Effect effect = null;

    //User input variables
    static int selectedPokemon = 0;
    static int choice = 0;
    static int tempSlot = 0;

    //A flag variable to detect if an invalid input has been put
    static boolean badInputError = false;

    public static void main(String[] args) { //Beginning of main method

        //Title ASCII
        System.out.println("""
                                                  ,'\\
                    _.----.        ____         ,'  _\\   ___    ___     ____
                _,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.
                \\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |
                 \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |
                   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
                    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |
                     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |
                      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |
                       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |
                        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |
                                                `'                            '-._|
                """);
        
        while(choice != 6) { //Start of main menu
            displayMainMenu();
            choice = promptInt();

            switch (choice){
                case 1: //Create a Pokemon
                    if(numOfPokemon < 6)
                        createPokemon();
                    else
                        System.out.println("\nERROR: You have a full team. Remove a Pokemon and try again.\n");
                    break;
                case 2: //Remove a Pokemon
                    if(numOfPokemon == 0) {
                        System.out.println("\nERROR: Your Pokemon team is empty.\n");
                    } else {
                        listPokemonNames();

                        System.out.print("\nWhich Pokemon do you want to remove (0 to go back) - \n");
                        tempSlot = promptInt()-1;

                        if(tempSlot == -1)
                            break;

                        removeOnePokemon(tempSlot);
                    }
                    break;
                case 3: //List the whole Pokemon team
                    listAllPokemon();
                    break;
                case 4: //Select one Pokemon (displays a submenu if input is valid)
                    if(numOfPokemon == 0) {
                        System.out.println("\nERROR: You have no Pokemon to select.\n");
                    } else {
                        while (true) {
                            listPokemonNames();

                            System.out.print("\nWhich Pokemon do you want to select? (0 to go back) - \n");
                            selectedPokemon = promptInt()-1;

                            if(selectedPokemon == -1)
                                break;

                            if(selectedPokemon > 5 || selectedPokemon < 0) {
                                System.out.println("\nERROR: Invalid input, must be between 1-6\n");
                            } else {
                                if(team[selectedPokemon] == null) {
                                    System.out.println("\nERROR: That slot is empty. Try again.");
                                } else {
                                    System.out.println("\nYou have selected '" + team[selectedPokemon].getName() +  "'\n");
                                    while(choice != 6) {
                                        selectPokemonMenu();
                                    }
                                    choice = 0; //Reset choice value so it doesn't go into the main loop
                                }
                            }
                        }
                    }
                    break;
                case 5: //Sort the Pokemon with a preferred method
                    sortPokemon();
                    break;
                case 6: //Exit
                    System.out.println("\nThank you for playing!");
                    break;
                default: //Input out of range
                    System.out.println("\nYou must select a number from 1-6.");
            }
        } //End of main menu
    } //End of main method

    //Prompts user for int input | Recurs when catching mismatch exception
    public static int promptInt() {
        int choice = 0;

        do {
            try {
                badInputError = false;
                choice = input.nextInt();
            } catch (InputMismatchException err) {
                System.out.print("You must enter an int. Try again - ");
                input.next();
                badInputError = true;
            }
        } while (badInputError);

        return choice;
    }

    //Prompts user for char input | Recurs when catching mismatch exception
    public static char promptChar() {
        char choice = ' ';

        do {
            try {
                badInputError = false;
                choice = Character.toUpperCase(input.next().charAt(0));
            } catch (InputMismatchException err) {
                System.out.print("You must enter a char. Try again - ");
                input.next();
                badInputError = true;
            }
        } while (badInputError);

        return choice;
    }

    //Prompts user for string input | Recurs when catching mismatch exception
    public static String promptString() {
        String choice = "";

        do {
            try {
                badInputError = false;
                choice = input.nextLine();
            } catch (InputMismatchException err) {
                System.out.print("You must enter a string. Try again - ");
                input.next();
                badInputError = true;
            }
        } while (badInputError);

        return choice;
    }

    //Recursive method that checks if the name input is valid
    public static void checkName() {
        if(name.length() == 0) {
            badInputError = true;
            System.out.print("Name cannot be empty. Try again - ");

            name = promptString();
            checkName();
        } else {
            if(!name.matches("[a-zA-Z\s]+")) {
                badInputError = true;
                System.out.print("Name cannot have numbers and symbols. Try again - ");

                name = promptString();
                checkName();
            } else {
                if(name.length() > 20) {
                    badInputError = true;
                    System.out.print("Name cannot have more than 20 characters. Try again - ");

                    name = promptString();
                    checkName();
                } else {
                    badInputError = false;
                }
            }
        }
    }

    //Recursive method that checks if the level input is valid
    public static void checkLevel() {
        do {
            if(level > 100) {
                badInputError = true;
                System.out.print("Level cannot be greater than 100. Try again - ");

                level = promptInt();
                checkLevel();
            } else if (level < 1) {
                badInputError = true;
                System.out.print("Level cannot be less than 1. Try again - ");

                level = promptInt();
                checkLevel();
            } else {
                badInputError = false;
            }
        } while (badInputError);
    }

    //Recursive method that checks if the accuracy input is valid (very similar to checkLevel())
    public static void checkAccuracy() {
        do {
            if(accuracy > 100) {
                badInputError = true;
                System.out.print("Accuracy cannot be greater than 100%. Try again - ");

                accuracy = promptInt();
                checkAccuracy();
            } else if (level < 1) {
                badInputError = true;
                System.out.print("Accuracy cannot be less than 1%. Try again - ");

                accuracy = promptInt();
                checkAccuracy();
            } else {
                badInputError = false;
            }
        } while (badInputError);
    }

    //Recursive method that checks if the gender input is valid
    public static void checkGender() {
        do {
            if(gender != 'M' && gender != 'F') {
                badInputError = true;
                System.out.print("Gender must be Male (M) or Female (F). Try again - ");

                gender = promptChar();
                checkGender();
            } else {
                badInputError = false;
            }
        } while (badInputError);
    }

    //Displays the main menu
    public static void displayMainMenu() {
        System.out.println("********************");
        System.out.println("      MAIN MENU     ");
        System.out.println("********************");
        System.out.println("1. Create a Pokemon");
        System.out.println("2. Remove a Pokemon");
        System.out.println("3. List all Pokemon");
        System.out.println("4. Select one Pokemon");
        System.out.println("5. Sort Pokemon");
        System.out.println("6. Exit");

        System.out.print("\nType a number to select an option: ");
    }

    //Prompts the user to select a Pokemon and brings up the selected Pokemon's menu
    public static void selectPokemonMenu() {
        System.out.println("******************");
        System.out.println(team[selectedPokemon].getName().toUpperCase());
        System.out.println("******************");
        System.out.println("1. Create a move");
        System.out.println("2. View Pokemon");
        System.out.println("3. List one move");
        System.out.println("4. List all moves");
        System.out.println("5. Remove a move");
        System.out.println("6. Go back");

        System.out.print("\nYour selection: ");
        choice = promptInt();

        processPokemonMenuInput();
    }

    //Takes the input from selectPokemonMenu() and processes it in a switch statement
    public static void processPokemonMenuInput() {
        switch(choice) {
            case 1: //Create a move
                if(team[selectedPokemon].getNumOfMoves() < 4)
                    createMove(team[selectedPokemon]);
                else
                    System.out.println("\nERROR: You cannot have more than 4 moves.");
                break;
            case 2: //View selected Pokemon
                listOnePokemon(selectedPokemon);
                break;
            case 3: //View one move
                if(team[selectedPokemon].getNumOfMoves() == 0) {
                    System.out.println("\nERROR: " + team[selectedPokemon].getName() + " has no moves.");
                } else {
                    listMoveNames(team[selectedPokemon]);

                    System.out.print("Which move do you want to view? - ");
                    tempSlot = promptInt() - 1;

                    listOneMove(team[selectedPokemon], tempSlot);
                }
                break;
            case 4: //View all the moves
                if(team[selectedPokemon].getNumOfMoves() == 0)
                    System.out.println("\nERROR: " + team[selectedPokemon].getName() + " has no moves.");
                else
                    listAllMoves(team[selectedPokemon]);
                break;
            case 5: //Remove one move
                if(team[selectedPokemon].getNumOfMoves() == 0) {
                    System.out.println("\nERROR: " + team[selectedPokemon].getName() + " has no moves.");
                } else {
                    listMoveNames(team[selectedPokemon]);

                    System.out.print("Which move do you want to remove? (0 to go back) - ");
                    tempSlot = promptInt() - 1;

                    if(tempSlot == -1)
                        break;

                    removeOneMove(team[selectedPokemon], tempSlot);
                }
                break;
            case 6: //Go back
                break;
            default:
                System.out.println("\nERROR: Invalid input, must input an int from 1-6.");
        }
    }

    //Lists all the status effects and prompts the user to select one
    public static void listAndPromptEffects() {
        //Table format
        String leftAlignFormat = "| %-15s | %-4d |%n";

        System.out.format("+-----------------+------+%n");
        System.out.format("| Status Effects  | ID   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < effects.length; i++) {
            System.out.format(leftAlignFormat, effects[i], (i+1));
        }
        System.out.format("+-----------------+------+%n");

        //Will repeat asking for the type until the user enters a valid response
        do {
            System.out.print("Effect: ");
            tempType = input.nextInt();

            try {
                effect = effects[tempType-1];
                badInputError = false;
            } catch (Exception err) {
                System.out.println("\nERROR: Input is invalid or out of range. Try again - ");
                badInputError = true;
            }
        } while (badInputError);
    }

    //Lists all the Pokemon/move types and prompts the user to pick one
    public static void listAndPromptTypes() {
        //Table format
        String leftAlignFormat = "| %-15s | %-4d |%n";

        System.out.format("+-----------------+------+%n");
        System.out.format("| Pokemon Types   | ID   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < types.length; i++) {
            System.out.format(leftAlignFormat, types[i], (i+1));
        }
        System.out.format("+-----------------+------+%n");

        //Will repeat asking for the type until the user enters a valid response
        do {
            System.out.print("Type: ");
            tempType = input.nextInt();

            try {
                type = types[tempType-1];
                badInputError = false;
            } catch (Exception err) {
                System.out.print("\nERROR: Input is invalid or out of range. Try again - ");
                badInputError = true;
            }
        } while (badInputError);
    }

    //Gives the user the input process to create a move for the selected Pokemon
    public static void createMove(Pokemon pokemon) {
        System.out.println("******************");
        System.out.println("CREATE YOUR MOVE!");
        System.out.println("******************");

        input.nextLine(); //dummy line

        System.out.print("Name: ");
        name = promptString();
        checkName();

        System.out.print("Power: ");
        power = promptInt();

        System.out.print("Accuracy (1-100%): ");
        accuracy = promptInt();
        checkAccuracy();

        listAndPromptTypes();
        listAndPromptEffects();

        //Move placed in the next available slot
        for(int i = 0; i < pokemon.getMoves().length; i++) {
            if(pokemon.getMoves()[i] == null) {
                pokemon.setMove(new Moves(name, type, power, accuracy, effect), i);
                break;
            }
        }
        pokemon.setNumOfMoves(pokemon.getNumOfMoves() + 1);

        System.out.println("\nSUCCESS: Move '" + name + "' successfully created for '" + pokemon.getName() + "'\n");
    }

    //Lists all the moves of the selected Pokemon
    public static void listAllMoves(Pokemon pokemon) {
        System.out.println("***************************");
        System.out.println(pokemon.getName().toUpperCase() + "'s MOVES");
        System.out.println("***************************");

        for(int i = 0; i < pokemon.getMoves().length; i++) {
            if(pokemon.getMoves()[i] != null) {
                System.out.println(pokemon.getMoves()[i].toString());
            }
        }

        System.out.println("***************************");
    }

    //Lists a move of choice from the selected Pokemon
    public static void listOneMove(Pokemon pokemon, int slot) {
        if(slot > 3 || slot < 0) {
            System.out.println("\nERROR: Invalid input, must be between 1 and 4");
        } else {
            if(pokemon.getMoves()[slot] != null)
                System.out.println(pokemon.getMoves()[slot].toString());
            else
                System.out.println("\nERROR: That slot is empty");
        }
    }

    //Removes a move of choice from the selected Pokemon
    public static void removeOneMove(Pokemon pokemon, int slot) {
        if(slot > 3 || slot < 0) {
            System.out.println("\nERROR: Invalid input, must be between 1 and 4.");
        } else {
            if(pokemon.getMoves()[slot] != null) {
                System.out.println("\nSUCCESS: Move '" + pokemon.getMoves()[slot].getName() + "' removed from '" + pokemon.getName() + "'");

                pokemon.getMoves()[slot] = null;
                pokemon.setNumOfMoves(pokemon.getNumOfMoves() - 1);
            } else {
                System.out.println("\nERROR: That slot is already empty.");
            }
        }
    }

    //Gives the user the input process to create a Pokemon
    public static void createPokemon() {
        System.out.println("********************");
        System.out.println("CREATE YOUR POKEMON!");
        System.out.println("********************");

        input.nextLine(); //dummy line

        System.out.print("Name: ");
        name = promptString();
        checkName(); //Runs input through a method which makes sure the name is only letters and between 1-20 letters

        //Debug name to create default Pokemon to quickly fill slots
        if(name.equals("test")) {
            team[numOfPokemon] = new Pokemon();
            numOfPokemon++;
            System.out.println("\nSUCCESS: Default Pokemon created!\n");
            return;
        }

        System.out.print("Level: ");
        level = promptInt();
        checkLevel(); //Runs input through a method which makes sure the level is between 1 and 100

        System.out.print("Health: ");
        health = promptInt();

        System.out.print("Gender (M/F): ");
        gender = promptChar();
        checkGender(); //Runs input through a method which makes sure the gender is 'M' or 'F'

        listAndPromptTypes();

        //Pokemon placed in the next available slot
        for(int i = 0; i < team.length; i++) {
            if(team[i] == null) {
                team[i] = new Pokemon(name, type, level, health, gender);
                break;
            }
        }
        numOfPokemon++;

        System.out.println("\nSUCCESS: Pokemon '" + name + "' successfully created!");
    }

    //Lists all the Pokemon on the team
    public static void listAllPokemon() {
        for(Pokemon pokemon: team) {
            if(pokemon != null)
                System.out.println(pokemon);
        }
    }

    //Lists one Pokemon of the user's choice
    public static void listOnePokemon(int slot) {
        if (slot > 5 || slot < 0) {
            System.out.println("\nERROR: Invalid input, must be between 1 and 6.");
        }
        else {
            if(team[slot] != null) {
                System.out.println(team[slot].toString());
            } else {
                System.out.println("\nERROR: That slot is empty.");
            }
        }
    }

    //Removes one Pokemon of the user's choice
    public static void removeOnePokemon(int slot) {
        if (slot > 5 || slot < 0) {
            System.out.println("\nERROR: Invalid input, must be between 1 and 6.");
        }
        else {
            if(team[slot] != null) {
                System.out.println("\nSUCCESS: Pokemon '" + team[tempSlot].getName() + "' successfully removed.");
                team[slot] = null;
                numOfPokemon--;
            } else {
                System.out.println("\nERROR: That slot is already empty.");
            }
        }
    }

    //Lists only the names of each Pokemon on the team
    public static void listPokemonNames() {
        System.out.println("*****************");
        System.out.println("YOUR POKEMON TEAM");
        System.out.println("*****************");

        for(int i = 0; i < team.length; i++) {
            if(team[i] != null)
                System.out.println((i+1) + ". " + team[i].getName());
        }
    }

    //Lists the moves of a selected Pokemon
    public static void listMoveNames(Pokemon pokemon) {
        System.out.println("*************************");
        System.out.println(pokemon.getName().toUpperCase() + "'s MOVES");
        System.out.println("*************************");

        for(int i = 0; i < pokemon.getMoves().length; i++) {
            if(pokemon.getMoves()[i] != null)
                System.out.println((i+1) + ". "+ pokemon.getMoves()[i].getName());
        }
    }

    //Opens up a menu that allows the user to choose their preferred sorting method
    public static void sortPokemon() {
        System.out.println("***********************");
        System.out.println("SELECT A SORTING METHOD");
        System.out.println("***********************");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Level");
        System.out.println("3. Sort by Health");
        System.out.println("4. Go back");

        System.out.print("\nYour selection: ");
        choice = promptInt();

        switch (choice) {
            case 1:
                Arrays.sort(team, Comparator.nullsLast(new NameComparator()));
                System.out.println("\nSUCCESS: Pokemon successfully sorted by name!");
                break;
            case 2:
                Arrays.sort(team, Comparator.nullsLast(new LevelComparator()));
                System.out.println("\nSUCCESS: Pokemon successfully sorted by level!");
                break;
            case 3:
                Arrays.sort(team, Comparator.nullsLast(new HealthComparator()));
                System.out.println("\nSUCCESS: Pokemon successfully sorted by health!");
                break;
            case 4:
                break;
            default:
                System.out.println("\nERROR: Invalid input, must be int from 1-4");
                break;
        }
    }
}
