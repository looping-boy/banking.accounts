package looping.boy;
import static looping.boy.DateFormat.*;
import java.time.LocalDateTime;
import static looping.boy.ListeDesExceptions.MontantNegatifException;

    // CLASSE DE CREATION DE PARAMETRES D'UNE TRANSACTION
public class Transaction {
    double amount;
    Type type;
    LocalDateTime datetime;
    Account origin;
    Account destination;
    String label;

    public double getAmount() {
        return amount;
    }
    public Type getType() {
        return type;
    }
    public Account getOrigin() {
        return origin;
    }

    public Transaction(double amount, Type type, Account origin, Account destination, String label) throws BankingException {
        MontantNegatifException(amount);
        this.amount = amount;
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.label = label;
        this.datetime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "\u001B[31m" + "\tAmount : " + amount + " euros, Type : " + type + ", Datetime : " + DateFormat(datetime) + ", Label : " + label + Couleur.COULEUR + "\n\t\t\t\t\t" ;
    }
}
