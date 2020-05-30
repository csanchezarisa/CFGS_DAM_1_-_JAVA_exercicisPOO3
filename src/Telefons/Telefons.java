package Telefons;

import java.util.ArrayList;
import java.util.Scanner;

public class Telefons {

    public Scanner teclat = new Scanner(System.in);
    public static ArrayList<Mobil> mobils = new ArrayList<Mobil>();

    public static void main(String args[]) throws InterruptedException {
        Telefons launcher = new Telefons();
        launcher.launch();
    }

    public void launch() throws InterruptedException {
        char opcioEscollida = ' ';

        do {
            netejarPantalla();
            mostrarMenuPrincipal();

            opcioEscollida = teclat.next().charAt(0);

            switch (opcioEscollida) {

                case '1': generarTelefon();
                    break;

                case '2': enviarMissatge();
                    break;

                case '3':
                    break;

                default: System.out.println("Introdueix una opció vàlida!");
                    stop();

            }
        }
        while (opcioEscollida != '3');
    }

    // Mostra el menú del principi del programa
    private void mostrarMenuPrincipal() {
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println("| 1. Generar telèfon mòbil                    |");
        System.out.println("| 2. Enviar missatge a un telèfon mòbil       |");
        System.out.println("| 3. Sortir                                   |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - +");
    }

    // Permet crear un telèfon mòbil nou
    private void generarTelefon() {
        Mobil mobilPerAfegir = new Mobil();

        mobils.add(mobilPerAfegir);
    }

    private void enviarMissatge() throws InterruptedException {

        netejarPantalla();

        if (mobils.size() < 2) {
            System.out.println("No s'han creat els suficients telèfons!");
            stop();
        }
        else {
            String numeroOrigen = " ";
            String numeroDesti = " ";
            int posicioTelOrigen = -1;
            int posicioTelDesti = -1;

            do {
                System.out.println("Introdueix el número origen:");
                numeroOrigen = teclat.next();

                if (!Mobil.comprovarUnicitatNumero(numeroOrigen))
                    System.out.println("No s'ha trobat aquest número, prova-ho de nou.");
            }
            while (!Mobil.comprovarUnicitatNumero(numeroOrigen));

            do {
                System.out.println("Introdueix el número destí:");
                numeroDesti = teclat.next();

                if (!Mobil.comprovarUnicitatNumero(numeroDesti))
                    System.out.println("No s'ha trobat aquest número, prova-ho de nou.");
            }
            while (!Mobil.comprovarUnicitatNumero(numeroDesti));

            posicioTelOrigen = cercaIndexNum(numeroOrigen);
            posicioTelDesti = cercaIndexNum(numeroDesti);

            if (posicioTelDesti >= 0 && posicioTelOrigen >= 0) {
                System.out.println("Introdueix el missatge a enviar:");
                String missatgePerEnviar = teclat.next();
                mobils.get(posicioTelDesti).rebreMissatge(numeroOrigen, missatgePerEnviar);

                netejarPantalla();
                System.out.println("El número " + mobils.get(posicioTelOrigen).getNumero() + " a enviar un missatge al número " + mobils.get(posicioTelDesti).getNumero());
                System.out.println("El número " + mobils.get(posicioTelDesti).getNumero() + " té " + mobils.get(posicioTelDesti).getNumeroMissatgesRebuts() + " missatges:");
                System.out.println("----------------------------------------------------------");
                System.out.println(mobils.get(posicioTelDesti).getMissatgesRebuts());

                System.out.println();
                System.out.println("Introdueix una tecla per sortir . . .");
                String espera = teclat.next();
            }

        }

    }

    public int cercaIndexNum(String numeroPerCercar) {

        int index = -1;

        if (mobils.size() >= 2) {
            for (int i = 0; i < mobils.size(); i ++) {
                if (mobils.get(i).getNumero().equals(numeroPerCercar)) {
                    index = i;
                    i = mobils.size();
                }
            }
        }

        return index;
    }


    // Neteja la pantalla amb espais en blanc
    public void netejarPantalla() {
        for (int i = 0; i <= 50; i++) {
            System.out.println();
        }
    }

    // Permet parar durant un segon el programa
    public void stop() throws InterruptedException {
        Thread.sleep(1000);
    }
}
