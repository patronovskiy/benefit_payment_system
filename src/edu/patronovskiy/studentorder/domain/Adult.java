package edu.patronovskiy.studentorder.domain;

import java.time.LocalDate;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class Adult extends Person {
    private String passportSeria;
    private String passportNumber;
    private LocalDate issueDate;    //дата выдачи паспорта
    private PassportOffice issueDepartment; //место выдачи
    private String university;
    private String studentId;   //номер студенческого билета

    public Adult() {
    }

    public Adult(final String surName, final String givenName, final String patronymic, final LocalDate dateOfBirth) {
        super(surName, givenName, patronymic, dateOfBirth);
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(final String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(final String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(final String university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(final String studentId) {
        this.studentId = studentId;
    }

}
