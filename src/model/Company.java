package model;

public class Company {

    public static final String[] TYPESCLIENT = { "Normal", "Plata", "Oro", "Platinum" };
    public static final double[] DISCOUNT = { 0.015, 0.03, 0.05 };

    private String name;

    private String commercialRNumber;

    private String dateCR;

    private double weightAccum;

    private double moneyAccum;

    private String typeClient;

    public Company(String name, String commercialRNumber, String dateCR) {

        typeClient = TYPESCLIENT[0];

        this.name = name;
        this.commercialRNumber = commercialRNumber;
        this.dateCR = dateCR;

        weightAccum = 0;
        moneyAccum = 0;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightAccum() {
        return weightAccum;
    }

    public void setWeightAccum(double weightAccum) {
        this.weightAccum = weightAccum;
    }

    public double getMoneyAccum() {
        return moneyAccum;
    }

    public void setMoneyAccum(double moneyAccum) {
        this.moneyAccum = moneyAccum;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public String getCommercialRNumber() {
        return commercialRNumber;
    }

    public String getDateCR() {
        return dateCR;
    }

    public void setDateCR(String dateCR) {
        this.dateCR = dateCR;
    }

}