package edu.patronovskiy.studentorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import edu.patronovskiy.studentorder.dao.DictionaryDaoImpl;
import edu.patronovskiy.studentorder.dao.StudentDaoImpl;
import edu.patronovskiy.studentorder.dao.StudentOrderDao;
import edu.patronovskiy.studentorder.domain.Address;
import edu.patronovskiy.studentorder.domain.Adult;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.CountryArea;
import edu.patronovskiy.studentorder.domain.PassportOffice;
import edu.patronovskiy.studentorder.domain.RegisterOffice;
import edu.patronovskiy.studentorder.domain.Street;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.domain.University;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class SaveStudentOrder {
    public static void main(String[] args) throws Exception { //todo
//        List<Street> d = new DictionaryDaoImpl().findStreets("про");
//        for (Street s : d) {
//            System.out.println(s.getStreetName());
//        }
//
//        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
//        for (PassportOffice p : po) {
//            System.out.println(p.getOfficeName());
//        }
//
//        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffices("010010000000");
//        for (RegisterOffice r : ro) {
//            System.out.println(r.getOfficeName());
//        }

//        List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
//        for (CountryArea area : ca1) {
//            System.out.println(area.getAreaId() + ": " + area.getAreaName());
//        }
//
//        List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
//        for (CountryArea area : ca2) {
//            System.out.println(area.getAreaId() + ": " + area.getAreaName());
//        }
//
//        List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020020000000");
//        for (CountryArea area : ca3) {
//            System.out.println(area.getAreaId() + ": " + area.getAreaName());
//        }
//
//        List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020020010000");
//        for (CountryArea area : ca4) {
//            System.out.println(area.getAreaId() + ": " + area.getAreaName());
//        }

        StudentOrder s = buildStudentOrder(10);
        StudentOrderDao dao = new StudentDaoImpl();
        Long id = dao.saveStudentOrder(s);
        System.out.println("id is " + id);

//        StudentOrder studentOrder = new StudentOrder();
//        long ans = saveStudentOrder(studentOrder);

    }

    static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 0;
        System.out.println("Student order saved");
        return answer;
    }

    public static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7, 4));
        RegisterOffice ro = new RegisterOffice(1L, "", "");
        so.setMarriageOffice(ro);

        Address address = new Address("195000", new Street(1L,"Street first"), "12", "", "142");

        // Муж
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        PassportOffice po1 = new PassportOffice(1L, "", "");
        husband.setIssueDepartment(po1);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUniversity(new University(2L, ""));
        husband.setStudentId("HH12345");

        // Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алекссевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        PassportOffice po2 = new PassportOffice(2L, "", "");
        wife.setIssueDepartment(po2);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUniversity(new University(1L, ""));
        wife.setStudentId("WW6789");

        // Ребенок
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 6, 30));
        RegisterOffice ro2 = new RegisterOffice(2L, "", "");
        child1.setIssueDepartment(ro2);
        child1.setAddress(address);

        // Ребенок
        Child child2 = new Child("Петрова", "Мария", "Викторовна", LocalDate.of(2019, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2019, 7, 19));
        RegisterOffice ro3 = new RegisterOffice(3L, "", "");
        child2.setIssueDepartment(ro3);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }


}
