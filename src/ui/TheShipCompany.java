package ui;

import model.*;

import java.util.Scanner;

public class TheShipCompany {

    private static MyShip elPirata;
    private static Scanner r;
    private static int opt;

    public TheShipCompany() {

    }

    public void welcomeMsg() {
        System.out.println("\n\nBIENVENIDO A LA MEJOR EMPRESA TRANSPORTISTA DEL LEJANO OESTE MARITIMO\n");

        System.out.println("********************************************************************");
        System.out.println("********************* EL PIRATA COMPANY S.A ************************");
        System.out.println("********************************************************************\n\n");

    }

    public void init() {

        r = new Scanner(System.in);
        opt = 0;

        System.out.println("Ingrese el nombre del capitan del barco");
        String nameCaptain = r.nextLine();

        elPirata = new MyShip(nameCaptain);
        System.out.println("Por favor agrege los 5 clientes: \n");
        Company clients[] = new Company[5];

        int numClients = 0;

        while (numClients < clients.length) {

            System.out.println("Cual es el numbre de la empresa no. " + (numClients + 1) + "?");
            String name = r.nextLine();
            System.out.println("Cual es el numero de registro mercantil de la empresa no. " + (numClients + 1) + "?");
            String commercialRNumber = r.nextLine();
            System.out.println("Cual es la fecha de expedicion del registro mercantil de la empresa no. "
                    + (numClients + 1) + "?");
            String dateCR = r.nextLine();

            clients[numClients] = new Company(name, commercialRNumber, dateCR);

            numClients++;

        }

        elPirata.setClients(clients);

    }

    public void addLoad() {

        System.out.println("Cual es la empresa propietaria de la carga?");

        for (int i = 0; i < elPirata.getClients().length; i++) {
            System.out.println((i + 1) + " " + elPirata.getClients()[i].getName());
        }

        int numClient = r.nextInt();
        Company owner = elPirata.getClients()[numClient - 1];
        System.out.println("Cual es el tipo de carga que se desea enviar?");
        for (int i = 0; i < Load.TYPELOAD.length; i++) {
            System.out.println((i + 1) + " " + Load.TYPELOAD[i]);
        }
        String typeLoad = Load.TYPELOAD[r.nextInt() - 1];
        System.out.println("Cuanto pesa cada caja (Gramos)?");
        double weightByLoad = (r.nextDouble() * 0.001);
        System.out.println("Cuantas cajas son? ");
        int numBoxes = r.nextInt();

        Load newLoad = new Load(typeLoad, weightByLoad, numBoxes, owner);

        System.out.println(elPirata.reciveLoad(newLoad));

        // verifica si el cliente es actualizable
        if (elPirata.canUpgrade(numClient)) {

            System.out.println(elPirata.upgradeClient(numClient));

        } else {
            System.out.println(
                    "Nota: Este cliente pronto sera actualizado de plan (Solo aplica para clientes diferentes de Platinum).");
        }

    }

    public void showMenu() {
        System.out.println(
                "Menu principal:\n\n\nEscoja la opcion que desee:\n\n(1) Cargar el barco\n(2) Descargar el barco para iniciar un nuevo viaje\n(3) Mostrar peso total de la carga que transporta el barco\n(4) Verificar si se puede zarpar\n(5) Salir");

    }

    public void emptyMyShip() {
        System.out.println(elPirata.resetShip());
    }

    public void showWeightActually() {
        System.out.println("El peso total de la carga hasta el momento es: " + elPirata.getTotalWeight() + "kilos");
    }

    public void checkSail() {
        System.out.println(elPirata.canSail());
    }

    public void exitMsg() {
        System.out.println("Muchas gracias por utilizar este software, adios");
    }

    public static void main(String[] args) {

        TheShipCompany myShip = new TheShipCompany();

        myShip.welcomeMsg();
        myShip.init();

        do {

            myShip.showMenu();
            opt = r.nextInt();

            switch (opt) {
                case 1:

                    myShip.addLoad();

                    break;

                case 2:
                    myShip.emptyMyShip();
                    break;

                case 3:
                    myShip.showWeightActually();
                    break;

                case 4:
                    myShip.checkSail();
                    break;

                case 5:

                    myShip.exitMsg();

                    break;

                default:
                    System.out.println("Ha digitado una opcion invalida, vuelva a intentar, gracias");
                    break;
            }

        } while (opt != 5);

        r.close();

    }
}