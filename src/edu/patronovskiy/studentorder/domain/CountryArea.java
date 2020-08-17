package edu.patronovskiy.studentorder.domain;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class CountryArea {
    private String areaId;
    private String areaName;

    public CountryArea() {
    }

    public CountryArea(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(final String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(final String areaName) {
        this.areaName = areaName;
    }
}
