package edu.patronovskiy.studentorder;

import edu.patronovskiy.studentorder.domain.AnswerChildren;
import edu.patronovskiy.studentorder.domain.AnswerCityRegister;
import edu.patronovskiy.studentorder.domain.AnswerStudent;
import edu.patronovskiy.studentorder.domain.AnswerWedding;
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
        StudentOrder[] soArray = readStudentOrders();
        for(int i = 0; i < soArray.length; i++) {
            checkOneOrder(soArray[i]);
            System.out.println(soArray[i].getStudentOrderId() + " is checked\n");
        }
    }

    public StudentOrder[] readStudentOrders() {
    StudentOrder[] soArray = new StudentOrder[3];
        for (int i = 0; i < soArray.length; i++) {
            soArray[i] = SaveStudentOrder.buildStudentOrder(i);
        }
    return soArray;
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
