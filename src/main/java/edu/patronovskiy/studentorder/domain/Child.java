package edu.patronovskiy.studentorder.domain;

import java.time.LocalDate;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class Child extends Person{
    private String certificateNumber;   //номер свидетельства о рождении
    private LocalDate issueDate;    //дата выдачи свидетельства о рождении
    private RegisterOffice issueDepartment; //место выдачи свидетельства о рождении (кем выдано)

    public Child(final String surName, final String givenName, final String patronymic, final LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(final String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public RegisterOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(final RegisterOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
