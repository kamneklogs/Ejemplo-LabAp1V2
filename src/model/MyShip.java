package model;

import java.util.ArrayList;

public class MyShip {

    public final static double WEIGHTLIMIT = 28000;

    private String nameCaptain;

    private ArrayList<Load> theLoads;
    private Company[] clients;

    private double totalWeight;

    public MyShip(String nameCamptain) {

        nameCaptain = nameCamptain;

        totalWeight = 0;

        theLoads = new ArrayList<Load>();

    }

    public String getNameCaptain() {
        return nameCaptain;
    }

    // calcula el costo de una nueva carga incluyemdo posibles descuentors, si el
    // cliente es de tipo "Normal" no tiene descuento
    public double costOfLoad(Load newLoad) {

        double total = 0;

        double weightTotal = 0;

        if (newLoad.getTypeLoad().equals(Load.TYPELOAD[0])) {

            weightTotal = newLoad.getWeightByLoad() * newLoad.getNumBoxes();
            total = weightTotal * 390000;

        } else if (newLoad.getTypeLoad().equals(Load.TYPELOAD[1])) {

            weightTotal = newLoad.getWeightByLoad() * newLoad.getNumBoxes();
            total = weightTotal * 250000;

        } else {

            weightTotal = newLoad.getWeightByLoad() * newLoad.getNumBoxes();
            total = weightTotal * 80000;

        }

        if (newLoad.getOwner().getTypeClient().equals(Company.TYPESCLIENT[1])
                && newLoad.getTypeLoad().equals(Load.TYPELOAD[1])) {
            total -= total * Company.DISCOUNT[0];
        }

        if (newLoad.getOwner().getTypeClient().equals(Company.TYPESCLIENT[2])
                && (newLoad.getTypeLoad().equals(Load.TYPELOAD[1]) || newLoad.getTypeLoad().equals(Load.TYPELOAD[2]))) {
            total -= total * Company.DISCOUNT[1];
        }

        if (newLoad.getOwner().getTypeClient().equals(Company.TYPESCLIENT[3])) {
            total -= total * Company.DISCOUNT[2];
        }

        return total;
    }

    public Company[] getClients() {
        return clients;
    }

    public void setClients(Company[] clients) {
        this.clients = clients;
    }

    public ArrayList<Load> getMyLoads() {
        return theLoads;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String reciveLoad(Load newLoad) {

        String result = "No se pudo cargar por politicas internas, se procedio a retirar todas sus cargas.";

        if (totalWeight < WEIGHTLIMIT) {

            switch (newLoad.getTypeLoad()) {

                case "PELIGROSA":

                    if (!existPerecedera()) {

                        result = "La carga fue recibida exitosamente, valor a pagar: $" + costOfLoad(newLoad);
                        totalWeight += newLoad.getWeightByLoad() * newLoad.getNumBoxes();
                        theLoads.add(newLoad);

                        for (int i = 0; i < clients.length; i++) {

                            if (clients[i].getName().equals(newLoad.getOwner().getName())) {
                                clients[i].setMoneyAccum(clients[i].getMoneyAccum() + costOfLoad(newLoad));
                                clients[i].setWeightAccum(clients[i].getWeightAccum()
                                        + (newLoad.getNumBoxes() * newLoad.getWeightByLoad()));
                            }

                        }

                    } else {
                        for (int i = 0; i < theLoads.size(); i++) {
                            if (theLoads.get(i).getOwner().getName().equals(newLoad.getOwner().getName())) {

                                totalWeight -= newLoad.getWeightByLoad() * newLoad.getNumBoxes();
                                theLoads.remove(i);
                            }
                        }
                    }

                    break;

                case "PERECEDERA":

                    if (!existPeligrosa()) {

                        result = "La carga fue recibida exitosamente, valor a pagar: $" + costOfLoad(newLoad);
                        totalWeight += newLoad.getWeightByLoad() * newLoad.getNumBoxes();
                        theLoads.add(newLoad);

                        for (int i = 0; i < clients.length; i++) {

                            if (clients[i].getName().equals(newLoad.getOwner().getName())) {
                                clients[i].setMoneyAccum(clients[i].getMoneyAccum() + costOfLoad(newLoad));
                                clients[i].setWeightAccum(clients[i].getWeightAccum()
                                        + (newLoad.getNumBoxes() * newLoad.getWeightByLoad()));
                            }

                        }

                    } else {
                        for (int i = 0; i < theLoads.size(); i++) {
                            if (theLoads.get(i).getOwner().getName().equals(newLoad.getOwner().getName())) {

                                totalWeight -= newLoad.getWeightByLoad() * newLoad.getNumBoxes();
                                theLoads.remove(i);
                            }
                        }
                    }

                    break;

                case "NO PERECEDERA":

                    result = "La carga fue recibida exitosamente, valor a pagar: $" + costOfLoad(newLoad);
                    totalWeight += newLoad.getWeightByLoad() * newLoad.getNumBoxes();
                    theLoads.add(newLoad);

                    for (int i = 0; i < clients.length; i++) {

                        if (clients[i].getName().equals(newLoad.getOwner().getName())) {
                            clients[i].setMoneyAccum(clients[i].getMoneyAccum() + costOfLoad(newLoad));
                            clients[i].setWeightAccum(
                                    clients[i].getWeightAccum() + (newLoad.getNumBoxes() * newLoad.getWeightByLoad()));
                        }

                    }

                    break;
            }

        }

        return result;
    }

    // verifica si hay carga perecedera
    public boolean existPerecedera() {

        for (Load l : theLoads) {

            if (l.getTypeLoad().equals("PERECEDERA")) {
                return true;
            }
        }

        return false;
    }

    // verifica si hay carga peligrosa
    public boolean existPeligrosa() {

        for (Load l : theLoads) {

            if (l.getTypeLoad().equals("PELIGROSA")) {
                return true;
            }
        }

        return false;
    }

    public String resetShip() {

        if (!theLoads.isEmpty()) {
            theLoads.clear();
            totalWeight = 0;
        } else {

            return "El barco ya esta vacio";
        }

        return "Barco descargado correctamente";

    }

    public boolean canUpgrade(int numClient) {

        switch (clients[numClient - 1].getTypeClient()) {

            case "Normal":

                if (clients[numClient - 1].getWeightAccum() >= 35000) {
                    return true;
                }

                break;

            case "Plata":

                if (clients[numClient - 1].getWeightAccum() >= 55000
                        || clients[numClient - 1].getMoneyAccum() >= 2000000) {
                    return true;
                }

                break;

            case "Oro":

                if (clients[numClient - 1].getMoneyAccum() >= 5000000) {
                    return true;
                }
                break;

        }

        return false;

    }

    public String upgradeClient(int numClient) {

        String msg = "";

        if (clients[numClient - 1].getTypeClient().equals("Normal")) {

            msg = "Felicidades!!! Este cliente subio de categoria a Plata";

        }

        if (clients[numClient - 1].getTypeClient().equals("Plata")) {

            msg = "Felicidades!!! Este cliente subio de categoria a Oro";

        }

        if (clients[numClient - 1].getTypeClient().equals("Oro")) {

            msg = "Felicidades!!! Este cliente subio de categoria a Platinum";

        }

        int p = 0;

        for (String s : Company.TYPESCLIENT) {
            if (s.equals(clients[numClient - 1].getTypeClient())) {
                break;
            }

            p++;
        }

        // se actualiza al siguiente nivel
        clients[numClient - 1].setTypeClient(Company.TYPESCLIENT[p + 1]);

        return msg;
    }

    // Metodo que retorna String que dice si se puede zarpar o no

    public String canSail() {

        String msg = null;

        if (theLoads.size() >= 2 || totalWeight >= 12000) {
            msg = "Zarpe autorizado";
        } else {
            msg = "Aun no esta autorizado para zarpar";
        }

        return msg;
    }

}