package model;

public class Load {

    public final static String[] TYPELOAD = { "PELIGROSA", "PERECEDERA", "NO PERECEDERA" };

    private String typeLoad;

    private double weightByLoad;

    private int numBoxes;

    private Company owner;

    public Load(String typeLoad, double weightByLoad, int numBoxes, Company owner) {

        this.typeLoad = typeLoad;
        this.weightByLoad = weightByLoad;
        this.numBoxes = numBoxes;

        this.owner = owner;
    }

    public String getTypeLoad() {
        return typeLoad;
    }

    public double getWeightByLoad() {
        return weightByLoad;
    }

    public int getNumBoxes() {
        return numBoxes;
    }

    public Company getOwner() {
        return owner;
    }

}