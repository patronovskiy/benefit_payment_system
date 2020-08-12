package edu.patronovskiy.studentorder.domain;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class Street {
    private Long streetCode;
    private String streetName;

    public Street() {
    }

    public Street(final Long streetCode, final String streetName) {
        this.streetCode = streetCode;
        this.streetName = streetName;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(final Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }
}
