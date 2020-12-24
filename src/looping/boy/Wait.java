package looping.boy;

import static looping.boy.Couleur.COULEUR;
    // FONCTION QUI GENERE UN AFFICHAGE DE CHARGEMENT (USELESS)
public class Wait {
    public static void print() {
        try {
            System.out.print(COULEUR + "\r_-''\tLoading");
            Thread.sleep(250);
            System.out.print("\r---");
            Thread.sleep(250);
            System.out.print("\r''-_\tLoading...");
            Thread.sleep(250);
            System.out.print("\r | ");
            Thread.sleep(250);
            System.out.print("\r");
        } catch (InterruptedException error) {
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
