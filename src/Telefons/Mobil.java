package Telefons;

import javax.swing.text.StringContent;
import java.util.Scanner;

public class Mobil {

    private String numero;
    private String missatgesRebuts;
    private int numeroMissatgesRebuts;
    public Scanner teclat = new Scanner(System.in);

    public Mobil() {
        System.out.println("Introdueix el número de mòbil:");
        String numero = teclat.next();
        setNumero(numero);
        this.numeroMissatgesRebuts = 0;
        this.missatgesRebuts = " ";
    }

    /* .: SETTERS :. */

    public void setNumero(String numero) {

        do {
            numero = convertirNumero(numero);

            while (!numero.matches("\\d*")) {
                System.out.println("Introdueix un número vàlid!");

                numero = teclat.next();
                numero = convertirNumero(numero);
            }

            if (comprovarUnicitatNumero(numero))
                System.out.println("Número repetit!");

        }
        while (comprovarUnicitatNumero(numero));

        this.numero = numero;
    }

    public void rebreMissatge(String emisor, String missatge) {
        this.numeroMissatgesRebuts ++;
        this.missatgesRebuts = missatgesRebuts + "\n" + emisor +": " + missatge;
    }

    /* .: GETTERS :. */

    public String getNumero() {
        return this.numero;
    }

    public int getNumeroMissatgesRebuts() {
        return this.numeroMissatgesRebuts;
    }

    public String getMissatgesRebuts() {
        return this.missatgesRebuts;
    }

    /* .: FUNCIONALITATS :. */

    // Talla el número introduir per que sempre tingui 9 digits
    private String convertirNumero(String numeroPerConvertir) {
        String numeroConvertit = " ";

        if (numeroPerConvertir.length() > 9) {
            for (int i = 0; i < 9; i++) {

                if (i == 0)
                    numeroConvertit = String.valueOf(numeroPerConvertir.charAt(i));
                else
                    numeroConvertit = numeroConvertit + numeroPerConvertir.charAt(i);

            }
        }
        else if (numeroPerConvertir.length() < 9) {

            numeroConvertit = numeroPerConvertir;

            for (int i = numeroPerConvertir.length(); i < 9; i++) {

                numeroConvertit = numeroConvertit + '0';

            }
        }
        else
            numeroConvertit = numeroPerConvertir;

        return numeroConvertit;
    }

    public static boolean comprovarUnicitatNumero(String numero) {
        boolean repetit = false;

        if (Telefons.mobils.size() > 0) {
            for (int i = 0; i < Telefons.mobils.size(); i++) {
                if (Telefons.mobils.get(i).getNumero().equals(numero)) {
                    repetit = true;
                    i = Telefons.mobils.size();
                }
            }
        }

        return repetit;
    }
}
