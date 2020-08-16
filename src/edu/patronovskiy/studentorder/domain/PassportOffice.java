package edu.patronovskiy.studentorder.domain;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class PassportOffice {
    private Long officeId;
    private String officeAreaId;
    private String officeName;

    public PassportOffice() {
    }

    public PassportOffice(Long officeId, String officeAreaId, String officeName) {
        this.officeId = officeId;
        this.officeAreaId = officeAreaId;
        this.officeName = officeName;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(final Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeAreaId() {
        return officeAreaId;
    }

    public void setOfficeAreaId(final String officeAreaId) {
        this.officeAreaId = officeAreaId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(final String officeName) {
        this.officeName = officeName;
    }
}
