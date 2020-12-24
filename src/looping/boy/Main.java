package looping.boy;

import java.time.format.DateTimeFormatter;

import static looping.boy.DateFormat.*;
import static looping.boy.Couleur.*;
import static looping.boy.Wait.*;

public class Main {
    // Creation d'un Iban, pour stocker des numéros d'IBAN dans une liste qui sera implémentée dans tt le programme
    public static Iban lesIbans = new Iban();

    public static void main(String[] args) {
        try {

            // Amazon EU
            Owner amazonEU = new CorporateOwner("Amazon EU SARL", new Adress("5 Rue Plaetis", null, "L2338", "Luxembourg", "Luxembourg"), "GB 727255821");
            Thread.sleep(10);
            Account amazonAccount = new Account("Corporate Account", amazonEU, 1_000_000_000);

            // Microsoft EU
            Owner microsoftEU = new CorporateOwner("Microsoft Limited", new Adress("Fao Carolyn Cheney", "Microsoft Campus", "RG1", "Reading", "UK"), "GB 724594615");
            Thread.sleep(10);
            Account microsoftAccount = new Account("Corporate Accountd", microsoftEU, 2_000_000_000);

            // Steve and Karim
            Adress steveAndKarimAddress = new Adress("3 Rives de Clausen", null, "L2165", "Lëtzebuerg", "Luxembourg");

            // Steve
            Owner steve = new PrivateOwner("Steve Belkin", steveAndKarimAddress);
            Thread.sleep(10);
            Account steveMainAccount = new Account("Compte courant", steve);
            Thread.sleep(10);
            Account steveSavingsAccount = new Account("Savings", steve, 2000);

            // Karim
            Owner karim = new PrivateOwner("Karim Khitmi", steveAndKarimAddress);
            Thread.sleep(10);
            Account karimAccount = new Account("Compte courant", karim, 2500);

            // Transactions
            microsoftAccount.transfer(3000, "SALARY FOR DECEMBER 2020", steveMainAccount);
            Thread.sleep(10);
            steveMainAccount.withdraw(300, "ATM Standard Withdrawal");
            Thread.sleep(10);
            steveMainAccount.transfer(25.00, "Purchase n°0123456", amazonAccount);
            Thread.sleep(10);
            steveMainAccount.transfer(500, "Epargne mensuelle", steveSavingsAccount);
            Thread.sleep(10);
            karimAccount.deposit(200, "Dépôt en agence");

            // Utilisation des toStrings() + des waits pour avoir des temps Date différent
            // pour la comparaison du getBalanceAt. Et pour que ça soit stylé pour la compilation.
            COULEUR = "\u001B[32m";
            Wait.print();
            System.out.println(COULEUR + amazonAccount + "\u001B[0m");
            COULEUR = "\u001B[33m";
            Wait.print();
            System.out.println(COULEUR + microsoftAccount + "\u001B[0m");
            COULEUR = "\u001B[34m";
            Wait.print();
            System.out.println(COULEUR + steveMainAccount + "\u001B[0m");
            COULEUR = "\u001B[35m";
            Wait.print();
            System.out.println(COULEUR + steveSavingsAccount + "\u001B[0m");
            COULEUR = "\u001B[36m";
            Wait.print();
            System.out.println(COULEUR + karimAccount + "\u001B[0m");

            // Resultat final des comptes
            System.out.println("RESULTAT DES COMPTES AU FINAL : ");

            COULEUR = "\u001B[32m";
            System.out.printf(COULEUR + "AmazonAccount : %f euros \u001B[0m\n", amazonAccount.getCurrentBalance());
            COULEUR = "\u001B[33m";
            Thread.sleep(200);
            System.out.printf(COULEUR + "MicrosoftAccount : %f euros \u001B[0m\n", microsoftAccount.getCurrentBalance());
            COULEUR = "\u001B[34m";
            System.out.printf(COULEUR + "SteveMainAccount : %f euros \u001B[0m\n", steveMainAccount.getCurrentBalance());
            Thread.sleep(400);
            System.out.println(COULEUR + "SteveMainAccount : " + steveMainAccount.getBalanceAt(steveMainAccount.transactionLog.get(1).datetime) + " euros" + "\u001B[31m" + ", à la DateTime : " + COULEUR + DateFormat(steveMainAccount.transactionLog.get(1).datetime) + "\u001B[0m");
            COULEUR = "\u001B[35m";
            Thread.sleep(200);
            System.out.printf(COULEUR + "SteveSavingsAccount : %f euros \u001B[0m\n", steveSavingsAccount.getCurrentBalance());
            COULEUR = "\u001B[36m";
            Thread.sleep(400);
            System.out.printf(COULEUR + "KarimAccount : %f euros \u001B[0m\n", karimAccount.getCurrentBalance());

            // Exception
        } catch (BankingException | InterruptedException error) {
            System.out.println(error.getMessage());
            error.printStackTrace();
        }
    }
}
