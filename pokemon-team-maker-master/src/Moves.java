public class Moves {
    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private Effect statusEffect;

    //Default constructor
    public Moves() {
        name = "-";
        type = null;
        power = 0;
        accuracy = 0;
        statusEffect = null;
    }

    //Secondary constructor
    public Moves(String name, Type type, int power, int accuracy, Effect statusEffect) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.statusEffect = statusEffect;
    }

    //Accessor methods
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Effect getStatusEffect() {
        return statusEffect;
    }

    //Mutator methods
    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setStatusEffect(Effect statusEffect) {
        this.statusEffect = statusEffect;
    }

    //toString method
    public String toString() {
        return  "\n<+><+><+><+><+><+><+><+><+>"  +
                "\nName: " + name +
                "\nType: " + type +
                "\nPower: " + power +
                "\nAccuracy: " + accuracy + "%" +
                "\nEffect: " + statusEffect +
                "\n<+><+><+><+><+><+><+><+><+>";
    }
}
