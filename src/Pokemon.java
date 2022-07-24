import java.util.*;

public class Pokemon {
    private String name;
    private Type type;
    private int level;
    private int health;
    private char gender;
    private Moves[] moves = new Moves[4];
    private int numOfMoves;

    //Default constructor
    public Pokemon() {
        name = "";
        type = null;
        level = 0;
        health = 0;
        gender = ' ';
        Arrays.fill(moves, null);
    }

    //Secondary constructor
    public Pokemon(String name, Type type, int level, int health, char gender) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.health = health;
        this.gender = gender;
        Arrays.fill(moves, null); //Pokemon will always start with no moves (user makes them later)
    }

    //Accessor methods
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public char getGender() {
        return gender;
    }

    public Moves[] getMoves() {
        return moves;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    //Mutator methods
    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    public void setMove(Moves move, int slot) {
        if(moves[slot] == null)
            moves[slot] = move;
        else
            System.out.println("That move slot is already taken.");
    }

    //Displays the name of the move | if move is null, it will just display a hyphen (-)
    public String displayMove(int slot) {
        String str;

        if(moves[slot] != null)
            str = moves[slot].getName();
        else
            str = "-";

        return str;
    }

    //Adds white space to short names to adjust name formatting
    public String formatAdjustName() {

        String extraStr = "";

        if (this.name.length() < 7) {
            extraStr = "     ";
        }

        return extraStr;
    }

    //Adds white space to short type enums to adjust type formatting
    public String formatAdjustType() {

        String extraStr = "";

        if (this.type != Type.ELECTRIC && this.type != Type.PSYCHIC && this.type != Type.FIGHTING) {
            extraStr = "     ";
        }

        return extraStr;
    }

    //toString method
    public String toString() {
        //Pokeball ASCII design
        return  "───███████████████████████───\n" +
                "─██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██─\n" +
                "██▓▓▓▓▓▓▓▓▓███████▓▓▓▓▓▓▓▓▓██\n" +
                "██▓▓▓▓▓▓▓▓██░░░░░██▓▓▓▓▓▓▓▓██\n" +
                " Name:   " + name + formatAdjustName() + "\tMove #1: " + displayMove(0) + "\n" +
                " Type:   " + type + formatAdjustType() + "\tMove #2: " + displayMove(1) + "\n" +
                " Level:  " + level +        "\t\tMove #3: " + displayMove(2) + "\n" +
                " Health: " + health +       "\t\tMove #4: " + displayMove(3) + "\n" +
                " Gender: " + gender +       "\n"  +
                "██░░░░░░░░██░░░░░██░░░░░░░░██\n" +
                "██░░░░░░░░░███████░░░░░░░░░██\n" +
                "─██░░░░░░░░░░░░░░░░░░░░░░░██─\n" +
                "───███████████████████████───\n";
        //Think I found a solution to the formatting issue. Will try to find a better non-hard coded solution eventually.
    }
}

//Comparator classes which allow me to easily sort the Pokemon objects by desired property

class NameComparator implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon poke1, Pokemon poke2) {
        return poke1.getName().compareTo(poke2.getName()); //Sorts Pokemon names from A-Z
    }
}

class LevelComparator implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon poke1, Pokemon poke2) {
        return poke2.getLevel() - poke1.getLevel(); //Sorts levels in descending order
    }
}

class HealthComparator implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon poke1, Pokemon poke2) {
        return poke2.getHealth() - poke1.getHealth(); //Sorts health in descending order
    }
}
