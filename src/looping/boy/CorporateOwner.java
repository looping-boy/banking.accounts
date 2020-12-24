package looping.boy;

public class CorporateOwner extends Owner{
    public String vatNumber;

    public CorporateOwner(String fullname, Adress adress, String vatNumber) {
        super(fullname, adress);
        this.vatNumber = vatNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "," + "\u001B[31m" + " VatNumber : " + Couleur.COULEUR + vatNumber;
    }
}
