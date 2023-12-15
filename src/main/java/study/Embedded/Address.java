package study.Embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address() {

    }


    public String getCity() {
        return city;
    }


    public String getStreet() {
        return street;
    }


    public String getZipcode() {
        return zipcode;
    }


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
