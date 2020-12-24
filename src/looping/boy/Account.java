package looping.boy;
import static looping.boy.DateFormat.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static looping.boy.ListeDesExceptions.*;
import static looping.boy.Main.lesIbans;
import static looping.boy.Type.*;

public class Account {
    String iban;
    String label;
    Owner owner;
    double initialBalance;
    LocalDateTime openingDateTime;
    List<Transaction> transactionLog = new ArrayList<>();   // LE TABLEAU COURANT QUI STOCK LES TRANSACTIONS

    public Account(String label, Owner owner) throws BankingException { //TODO : Pk Faut un throw là
        SiLabelOuOwnerNullException(label, owner);
        PlusDeCinquanteException(label);
        this.owner = owner;
        this.initialBalance = 0;
        this.iban = "FR " + lesIbans.creationIban();    // VA CHERCHER A FABRIQUER UN IBAN
        this.openingDateTime = LocalDateTime.now();
        this.label = label;
    }

    public Account(String label, Owner owner, double initialBalance) throws BankingException {
        SiLabelOuOwnerNullException(label, owner);
        PlusDeCinquanteException(label);
        this.label = label;
        this.owner = owner;
        this.initialBalance = initialBalance;
        this.iban = "FR " + lesIbans.creationIban();    // VA CHERCHER A FABRIQUER UN IBAN
        this.openingDateTime = LocalDateTime.now();
    }

    public void transfer(double amount, String label, Account recipient) throws BankingException {
        // EXCEPTIONS :
        SiAmountOuLabelOuRecipientException(amount, label, recipient);
        MemeOrigineEtDestinataireException(this, recipient);
        MontantNegatifException(amount);
        PlusDeCinquanteException(label);
        // MISE DANS TABLEAU DES TRANSACTIONS :
        addTransactionToLog(new Transaction(amount, TRANSFER, this, recipient, label));
        recipient.addTransactionToLog(new Transaction(amount, TRANSFER, this, recipient, label));
    }

    public void deposit(double amount, String label) throws BankingException {
        // EXCEPTIONS :
        SiAmoutOuLabelException(amount, label);
        MontantNegatifException(amount);
        PlusDeCinquanteException(label);
        // MISE DANS TABLEAU DES TRANSACTIONS :
        addTransactionToLog(new Transaction(amount, DEPOSIT, null, this, label));
    }

    public void withdraw(double amount, String label) throws BankingException {
        // EXCEPTIONS :
        MontantNegatifException(amount);
        PlusDeCinquanteException(label);
        // MISE DANS TABLEAU DES TRANSACTIONS:
        addTransactionToLog(new Transaction(amount, WITHDRAWAL, null, this, label));
    }

        // OK ON Y MET DANS LA LISTE :
    public void addTransactionToLog(Transaction transaction) {
        this.transactionLog.add(transaction);
    }

        // OK ON CALCULE LA SOMME DES TRANSACTIONS DE TOUTE LA LISTE
    public double getCurrentBalance() throws BankingException {
        double currentBalance = this.initialBalance;
        for (Transaction transaction : transactionLog) {
            // ON Y ENVOI DANS LA FONCTION QUI REGARDE SI C'EST TRANSFERT, DEPOSIT, OU WITHDRAWAL
            currentBalance = this.cases(transaction, currentBalance);
        }
        MontantNegatifException(currentBalance);
        return currentBalance;
    }

        // OK ON CALCULE LA SOMME DES TRANSACTIONS DE LA CREATION DE LA LISTE, JUSQU'A UN TEMPS DONNE
    public double getBalanceAt(LocalDateTime reference) {
        double currentBalance = this.initialBalance;
        for (Transaction transaction : transactionLog) {
            if (transaction.datetime.isBefore(reference)) {
                // ON Y ENVOI DANS LA FONCTION QUI REGARDE SI C'EST TRANSFERT, DEPOSIT, OU WITHDRAWAL
                currentBalance = this.cases(transaction, currentBalance);
            } else if (transaction.datetime.isEqual(reference)) {
                // ON Y ENVOI DANS LA FONCTION QUI REGARDE SI C'EST TRANSFERT, DEPOSIT, OU WITHDRAWAL
                currentBalance = this.cases(transaction, currentBalance);
            } else {
                System.out.print("");
            }

        }
        return currentBalance;
    }
        // POUR EVITER DE SE TAPER LE BORDEL TROIS FOIS, REGARDE SI C'EST TRANSFERT, DEPOSIT, OU WITHDRAWAL
    public double cases(Transaction transaction, double currentBalance) {
        if (transaction.getType().equals(TRANSFER)) {
            if (transaction.getOrigin() == this) {
                currentBalance -= transaction.getAmount();
            } else {
                currentBalance += transaction.getAmount();
            }
        } else if (transaction.getType().equals(DEPOSIT)) {
            currentBalance += transaction.getAmount();
        } else if (transaction.getType().equals(WITHDRAWAL)) {
            currentBalance -= transaction.getAmount();
        }
        return currentBalance;
    }

    @Override
    public String toString() {
        return "IBAN : " + iban + "\nLABEL : " + label + "\nOWNER : " + owner +
                "\nINITIAL BALANCE : " + initialBalance + " euros "+ "\nOPENING DATE : " +
                DateFormat(openingDateTime) + "\nTRANSACTION LOG : \t" + transactionLog + "\u001B[0m" +
                "\n ----------------------------------------------------------------------";
    }

}
