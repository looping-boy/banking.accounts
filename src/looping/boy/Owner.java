package looping.boy;
    // LA CLASSE OWNER
public abstract class Owner {
    public String fullname;
    public Adress adress;

    public Owner(String fullname, Adress adress) {
        this.fullname = fullname;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return fullname + adress;
    }
}
