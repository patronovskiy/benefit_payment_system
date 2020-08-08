package edu.patronovskiy.studentorder.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class StudentOrder {
    private long studentOrderId;
    private Adult husband;
    private Adult wife;
    private List<Child> children;
    private String marriageCertificateId;
    private String marriageOffice;
    private LocalDate marriageDate;

    public long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(final long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(final Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(final Adult wife) {
        this.wife = wife;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        if(children == null) {
            children = new ArrayList<>(5);  //в подавляющем большинстве случаев в молодой семье не более
                                                         // 2-3 детей, поэтому начального значения 5 обычно будет хватать
        }
        children.add(child);
    }

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public String getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(String marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }
}
