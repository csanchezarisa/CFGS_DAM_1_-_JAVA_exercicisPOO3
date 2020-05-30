package Banca;

import java.util.Scanner;

public class Compte {

    public Scanner teclat = new Scanner(System.in);
    private String propietari;
    private double saldo;
    public final static int LLARGNOM = 8; // Caràcters que pot tenir, com a màxim, el nom del propietari

    public Compte() {
        System.out.println("Introdueix el nom del propietari");
        setPropietari(teclat.nextLine());
        this.saldo = 0;
    }

    /* .: SETTERS :. */

    public void setPropietari(String nom) {
        nom = revisarNom(nom);

        while (comprovarNomUnic(nom)) {
            System.out.println("El nom del propietari està repetit, introdueix-ne un altre...");
            nom = revisarNom(teclat.nextLine());
        }

        this.propietari = nom;
    }

    public void actualitzarSaldo(double saldo) {
        this.saldo += saldo;
    }

    /* .: GETTERS :. */

    public String getPropietari() {
        return this.propietari;
    }

    public double getSaldo() {
        return this.saldo;
    }

    /* .: FUNCIONALITATS :. */

    // S'encarrega de comprovar que el nom està en majuscules i no passa el màxim nombre de caràcters decidit
    private String revisarNom(String nom) {
        String nomRevisat = " ";

        if (nom.length() > LLARGNOM) {
            for (int i = 0; i < LLARGNOM - 1; i++) {
                if (i == 0)
                    nomRevisat = String.valueOf(Character.toUpperCase(nom.charAt(0)));

                nomRevisat = nomRevisat + Character.toUpperCase(nom.charAt(i));
            }
        }
        else {
            for (int i = 0; i < nom.length(); i++) {
                if (i == 0)
                    nomRevisat = String.valueOf(Character.toUpperCase(nom.charAt(i)));
                else
                    nomRevisat = nomRevisat + Character.toUpperCase(nom.charAt(i));
            }
        }

        return nomRevisat;
    }


    // Comprova que el nom del propietari no es repeteix, així la posterior cerca per usuari sempre serà exacta
    public boolean comprovarNomUnic(String nomPerComprovar) {
        boolean repetit = false;

        if (Banca.comptes != null) {
            for (int i = 0; i < Banca.comptes.size(); i++) {
                if (Banca.comptes.get(i).propietari.equals(nomPerComprovar)) {
                    repetit = true;
                    i = Banca.comptes.size();
                }
            }
        }

        return repetit;
    }
}
