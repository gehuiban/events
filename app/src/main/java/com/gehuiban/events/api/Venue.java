package com.gehuiban.events.api;
/*
        "state": "FL",
        "city": "Palm Coast",
        "street": "200 Ocean Crest Dr",
        "country": "United States",
        "zipcode": "32137",
        "address": "200 Ocean Crest Dr, Palm Coast, FL 32137, USA"
 */


public class Venue {
    private String state;
    private String city;
    private String street;
    private String country;
    private String zipcode;
    private String address;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
