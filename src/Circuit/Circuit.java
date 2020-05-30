package Circuit;

import java.util.Scanner;

public class Circuit {

    public Scanner teclat = new Scanner(System.in);

    public static void main(String args[]) {
        Circuit launcher = new Circuit();
        launcher.launch();
    }

    public void launch() {

        Bombeta bombeta = new Bombeta();

        while (true) {
            System.out.println("La bombeta està " + redactarEstat(bombeta.getEstatBombeta()));
            System.out.println("-------------------------------------------------");
            System.out.println("Introdueix una tecla per activar l'interruptor . . .");
            String espera = teclat.next();
            Interruptor.modificarEstatBombeta(bombeta);
        }
    }

    private String redactarEstat(boolean estat) {
        String missatge;

        if (estat)
            missatge = "encesa";
        else
            missatge = "apagada";

        return missatge;
    }
}
