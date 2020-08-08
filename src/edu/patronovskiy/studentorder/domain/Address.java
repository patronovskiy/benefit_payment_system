package edu.patronovskiy.studentorder.domain;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class Address {
    private String street;
    private String building;
    private String extension;   //№ корпуса дома
    private String appartment;  //№ квартиры
    private String postCode;

    public Address() {
    }

    public Address(String postCode, String street, String building, String extension, String appartment) {
        this.postCode = postCode;
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.appartment = appartment;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(final String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(final String extension) {
        this.extension = extension;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(final String appartment) {
        this.appartment = appartment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }
}
