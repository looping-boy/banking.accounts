package looping.boy;

    // CLASSE QUI REPERTORIE LES EXCEPTIONS, POUR EVITER DE SE LES TAPER 15 0000 FOIS, ET POUR QUE LES CLASSES SOIENT LISIBLES

public class ListeDesExceptions {
    public static void PlusDeCinquanteException(String label) throws BankingException {
        if (label.length() > 50) {
            throw new BankingException("Label trop grand (50 Caractères)");
        }
    }   // TEST OK
    public static void MontantNegatifException(double currentBalance) throws BankingException {
        if (currentBalance < 0) {
            throw new BankingException("Transaction impossible, pas assez d'argent !");
        }
    }   // TEST OK
    public static void MemeOrigineEtDestinataireException(Account origin, Account dest) throws BankingException {
        if (origin.equals(dest)) {
            throw new BankingException("Même origine et destinataire !");
        }
    }   //TODO : TEST
    public static void SiLabelOuOwnerNullException(String label, Owner owner) throws BankingException {
        if (label.equals(null) || owner.equals(null)) {
            throw new BankingException("Il y a un paramètre obligatoire qui est 'null' !");
        }
    }   //TODO : TEST
    public static void SiAmountOuLabelOuRecipientException(double amount, String label, Account recipient) throws BankingException {
        if (label.equals(null) || recipient.equals(null) || (amount == 0)) {
            throw new BankingException("Il y a un paramètre obligatoire qui est 'null' ou à '0'!");
        }
    }   //TODO : TEST
    public static void SiAmoutOuLabelException(double amount, String label) throws BankingException {
        if (label.equals(null) || (amount == 0)) {
            throw new BankingException("Il y a un paramètre obligatoire qui est 'null' ou à '0'!");
        }
    }   //TODO : TEST
}
