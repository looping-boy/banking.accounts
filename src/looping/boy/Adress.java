package looping.boy;

public class Adress {
    String line1;
    String line2;
    String zipCode;
    String city;
    String country;

    public Adress(String line1, String line2, String zipCode, String city, String country) {
        this.line1 = line1;
        this.line2 = line2;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        if (this.line2 == null) {
            this.line2 = "";
        }
        return ", Adresse : " + line1 + " " + line2 + ", " + zipCode + ", " + city + ", " + country;
    }
}
