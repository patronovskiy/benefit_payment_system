package edu.patronovskiy.studentorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import edu.patronovskiy.studentorder.domain.Address;
import edu.patronovskiy.studentorder.domain.Adult;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.StudentOrder;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class SaveStudentOrder {
    public static void main(String[] args) throws Exception { //todo
        //регистрация драйвера в подсистеме jdbc, необязательно с версии спецификации 4.0
        //Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/jc_student",
            "postgres", "Postgresql72");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM jc_street");
        while(rs.next()) {
            System.out.println(rs.getLong(1) + " : " + rs.getString(2));
        }

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
        so.setMarriageOffice("Отдел ЗАГС");

        Address address = new Address("195000", "Заневский пр.", "12", "", "142");

        // Муж
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment("Отдел милиции №" + id);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        // Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алекссевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        wife.setIssueDepartment("Отдел милиции №" + id);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        // Ребенок
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 7, 19));
        child1.setIssueDepartment("ОТдел ЗАГС №" + id);
        child1.setAddress(address);
        // Ребенок
        Child child2 = new Child("Петрова", "Мария", "Викторовна", LocalDate.of(2019, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2019, 7, 19));
        child2.setIssueDepartment("ОТдел ЗАГС №" + id);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }


}
