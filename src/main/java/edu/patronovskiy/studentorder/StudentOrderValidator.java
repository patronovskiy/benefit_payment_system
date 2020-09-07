package edu.patronovskiy.studentorder;

import java.util.LinkedList;
import java.util.List;
import edu.patronovskiy.studentorder.domain.children.AnswerChildren;
import edu.patronovskiy.studentorder.domain.register.AnswerCityRegister;
import edu.patronovskiy.studentorder.domain.student.AnswerStudent;
import edu.patronovskiy.studentorder.domain.wedding.AnswerWedding;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.mail.MailSender;
import edu.patronovskiy.studentorder.validator.ChildrenValidator;
import edu.patronovskiy.studentorder.validator.CityRegisterValidator;
import edu.patronovskiy.studentorder.validator.StudentValidator;
import edu.patronovskiy.studentorder.validator.WeddingValidator;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator() {
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll() {
        List<StudentOrder> soList = readStudentOrders();
        for(StudentOrder so : soList) {
            checkOneOrder(so);
            System.out.println(so.getStudentOrderId() + " is checked\n");
        }
    }

    public List<StudentOrder> readStudentOrders() {
    List<StudentOrder> soList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            soList.add(so);
        }
    return soList;
    }

    public void checkOneOrder(StudentOrder so) {
        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding wedAnswer = checkWedding(so);
        AnswerChildren childAnswer = checkChildren(so);
        AnswerStudent studentAnswer = checkStudent(so);
        sendMail(so);
    }

    //проверки
    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        return cityRegisterVal.checkCityRegister(so);
    }

    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingVal.checkWedding(so);
    }

    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenVal.checkChildren(so);
    }

    public AnswerStudent checkStudent(StudentOrder so) {
        return studentVal.checkStudent(so);
    }

    public void sendMail (StudentOrder so){
        mailSender.sendMail(so);
    }

}
