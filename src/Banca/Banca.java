package Banca;

import javax.management.MBeanRegistration;
import java.util.ArrayList;
import java.util.Scanner;

public class Banca {

    public Scanner teclat = new Scanner(System.in);
    public static ArrayList<Compte> comptes = new ArrayList<Compte>();

    // Desfem les els mètodes statics
    public static void main(String args[]) throws InterruptedException {
        Banca launcher = new Banca();
        launcher.launch();
    }

    private void launch() throws InterruptedException {
        char opcio = ' ';

        do {
            mostrarMenuPrincipal();

            opcio = teclat.next().charAt(0);

            switch (opcio) {
                case '1': crearCompte();
                    break;

                case '2': modificarCompte();
                    break;

                case '3': visualitzarComptes();
                    break;

                case '4':
                    break;

                default: System.out.println("Introdueix una opció vàlida!");
                    stop();
            }

            netejaPantalla();

        } while (opcio != '4');
    }

    // Mostra el menú principal del programa
    private void mostrarMenuPrincipal() {
        System.out.println("+ - - - - - - - - - - - - - - - +");
        System.out.println("| 1. Crear compte nou           |");
        System.out.println("| 2. Modificar compte           |");
        System.out.println("| 3. Visualitzar comptes        |");
        System.out.println("| 4. Sortir                     |");
        System.out.println("+ - - - - - - - - - - - - - - - +");
    }


    // Mètode que crea un compte nou i l'afegeix a l'ArrayList de comptes
    private void crearCompte() {
        Compte nouCompte = new Compte();
        comptes.add(nouCompte);
    }


    // Deixa que l'usuari seleccioni el compte al que vol aplicar les modificacions
    private void modificarCompte() throws InterruptedException {
        if (comptes.size() == 0) {
            System.out.println("No hi ha cap compte creat! N'has de crear un de nou");
            stop();
        }
        else {
            int compteEscollida = -1;

            do {
                netejaPantalla();
                mostrarMenuModificarCompte();
                String decisioUsuari = teclat.next();
                if (esNumeric(decisioUsuari)) {
                    compteEscollida = Integer.parseInt(decisioUsuari);

                    if (compteEscollida >= 0 && compteEscollida <= comptes.size() + 1) {

                        if (compteEscollida == 0) {
                            compteEscollida = cercarCompte();
                        }

                        if (compteEscollida > 0 && compteEscollida <= comptes.size()) {
                            seleccionarModificacioCompte(compteEscollida - 1);
                        }
                        else if (!(compteEscollida == comptes.size() + 1)) {
                            System.out.println("No s'ha trobat el compte... Torna-ho a intentar...");
                            stop();
                        }

                    }
                    else {
                        System.out.println("Introdueix un número vàlid!");
                        stop();
                    }

                }
                else {
                    System.out.println("Introdueix un número!");
                    stop();
                }
            } while (compteEscollida < 0 || compteEscollida > comptes.size() + 1);
        }
    }

    // Mostra el menú per seleccionar el compte a modificar
    private void mostrarMenuModificarCompte() throws InterruptedException {
        System.out.println("+ - - - - - - - - - - +");
        System.out.println("| 0. Cerca            |");

        for (int i = 0; i < comptes.size(); i++) {
            String espais = " ";
            String compteInfo = (i + 1) + ". " + comptes.get(i).getPropietari();
            for (int x = 1; x < (20 - compteInfo.length()); x++)
                espais = espais + " ";
            System.out.println("| " + compteInfo + espais + "|");
        }
        System.out.println("| " + (comptes.size() + 1 + ". Sortir           |"));
        System.out.println("+ - - - - - - - - - - +");
    }


    // Permet a l'usuari escollir quina modificació vol fer-li al compte
    private void seleccionarModificacioCompte(int compte) throws InterruptedException {
        char opcioEscollida = ' ';

        do {
            netejaPantalla();
            mostrarMenuModificacionsCompte(compte);
            opcioEscollida = teclat.next().charAt(0);

            switch (opcioEscollida) {
                case '1': ingresarSaldo(compte);
                    break;

                case '2': retirarSaldo(compte);
                    break;

                case '3': canviarPropietari(compte);
                    break;

                case '4': eliminarCompte(compte);
                    opcioEscollida = '5'; // D'aquesta manera, quan s'esborri el compte sortirà del bucle
                    break;

                case '5':
                    break;

                default: System.out.println("Introdueix un punt de menú vàlid!");
                    stop();
            }

        } while (opcioEscollida != '5');
    }

    // Mostra el menú amb la informació del compte i les modificacions que es poden fer
    private void mostrarMenuModificacionsCompte(int compte) {
        System.out.println("Benvolgut: " + comptes.get(compte).getPropietari());
        System.out.println("El saldo actual és: " + comptes.get(compte).getSaldo());
        System.out.println();

        System.out.println("Què vols fer?");
        System.out.println("-------------------------");
        System.out.println(" 1. Ingresar");
        System.out.println(" 2. Retirar");
        System.out.println(" 3. Canviar propietari");
        System.out.println(" 4. Eliminar el compte");
        System.out.println(" 5. Sortir");
    }


    // Ingresa el saldo quan la quantitat és positiva
    private void ingresarSaldo(int comtpe) {
        System.out.println("Quants diners vols afegir-hi?");
        double quantitatPerAfegir = teclat.nextDouble();

        if (quantitatPerAfegir > 0)
            comptes.get(comtpe).actualitzarSaldo(quantitatPerAfegir);
    }


    // Retira la quantiat de saldo especificada. Sempre que sigui un nombre positiu i no deixi el saldo en negatiu
    private void retirarSaldo(int compte) throws InterruptedException {
        System.out.println("Quina quantiat vols retirar?");
        double quantitatPerRetirar = teclat.nextDouble();

        if ((quantitatPerRetirar > 0) && ((comptes.get(compte).getSaldo() - quantitatPerRetirar) >= 0))
            comptes.get(compte).actualitzarSaldo(-quantitatPerRetirar);
        else {
            System.out.println("No es pot retirar aquesta quantitat");
            stop();
        }
    }


    // Permet canviar el nom del propietari del compte
    private void canviarPropietari(int compte) {
        System.out.println("Introdueix el nom del nou propietari:");
        comptes.get(compte).setPropietari(teclat.next());
    }


    // Permet eliminar el compte especificat
    private void eliminarCompte(int compte) {
        System.out.println("Estas segur que vols esborrar el compte de " + comptes.get(compte).getPropietari() + "?");
        System.out.println(" S - Si");

        if (teclat.next().toUpperCase().charAt(0) == 'S')
            comptes.remove(compte);
    }


    private void visualitzarComptes() throws InterruptedException {
        netejaPantalla();

        if (comptes.size() == 0) {
            System.out.println("No hi ha cap compte creat! N'has de crear un de nou");
            stop();
        }
        else {
            for (int i = 0; i < comptes.size(); i++) {
                System.out.println("El client: " + comptes.get(i).getPropietari() + " té un saldo de " + comptes.get(i).getSaldo() + " euros");
            }
            System.out.println("Introdueix alguna tecla per continuar . . .");
            String premerReturn = teclat.next();
        }
    }


    // S'encarrega de cercar en tot el llistat de comptes el propietari que coincideix amb el que introdueix l'usuari
    public int cercarCompte() {
        System.out.println("Introdueix el nom del propietari a cercar");
        String propietariPerCercar = teclat.next();
        int resultatCerca = -1;

        if (comptes.size() > 0) {
            for (int i = 0; i < comptes.size(); i++) {
                if (comptes.get(i).getPropietari().matches(propietariPerCercar.toUpperCase())) {
                    resultatCerca = i;
                    i = comptes.size();
                }
            }
        }

        return (resultatCerca + 1);
    }


    // Imprimeix espais per pantalla per "netejar la pantalla"
    public void netejaPantalla() {
        for (int i = 0; i <= 50; i++)
            System.out.println();
    }

    // Pausa el programa
    public void stop() throws InterruptedException {
        Thread.sleep(1000);
    }

    // Revisa si el String que se li pasa conté números
    public boolean esNumeric(String frasePerAnalitzar) {
        boolean esNumeric = false;

        if (frasePerAnalitzar.matches("\\d*"))
            esNumeric = true;

        return esNumeric;
    }

}
