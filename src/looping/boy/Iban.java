package looping.boy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Iban {
    public static List<String> iban;
    public int i;
    String finalIban;

    /*
    GENERATION D'UN IBAN ALEATOIRE, QUI N'A PAS ENCORE ETE GENERE PAR UN AUTRE USER !!!!
     */

    public Iban() {
        this.iban = new ArrayList<>();  // BOOM, CREATION DE LA LISTE A PARTIR DU MAIN. ON Y TOUCHE PLUS !
                                        // ON PEUT JUSTE AJOUTER DES IBAN DEDANS
    }

    public String creationIban() {
        String finalResult = numberRandom();
        boolean good = false;
        // BOOM PREMIER APPEL, ON ADD LE PREMIER IBAN CREE DANS LA LISTE D'IBANS
        if (i == 0) {
            iban.add(finalResult);
            i++;
        // POUR LES SUIVANTS, ON REGARDE SI ILS EXISTENT PAS DEJA, SI OUI, ON RE-CREE ENCORE ET ENCORE
        } else {
            do {
                if (this.Matches(finalResult)) {
                    finalResult = numberRandom();
                    good = false;
                } else {
                    iban.add(finalResult);
                    i++;
                    good = true;
                }
            } while (!good);
        }
        return finalResult;
    }

        // CREATION D'UN IBAN RANDOM entre 1 et 999
    public String numberRandom() {
        Random r = new Random();
        int valeur = 1 + r.nextInt(999 - 1);
        String result = Integer.toString(valeur);           // ON PASSE EN STRING
        while (result.length() < 3) result = "0" + result;  // FAUT AVOIR 3 CHIFFRES "000"
        return result;

    }

        // EST CE Qu'IL Y A DEJA UN IBAN COMME CELUI CI -> TEST
    public boolean Matches(String chaine) {
        for (int j = 0; j < iban.toArray().length; j++) {
            if (iban.get(j).equals(chaine)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return finalIban;
    }
}
