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
    public static void main(String[] args) {
        checkAll();
    }

    private static void checkAll() {
        StudentOrder so = readStudentOrder();
        while (true) {
            if (so == null) {
                break;
            }

            AnswerCityRegister cityAnswer = checkCityRegister(so);
            if (!cityAnswer.success) {
                continue;
            }
            AnswerWedding wedAnswer = checkWedding(so);
            AnswerChildren childAnswer = checkChildren(so);
            AnswerStudent studentAnswer = checkStudent(so);

            sendMail(so);
        }
    }


    //проверки
    static StudentOrder readStudentOrder() {
        new StudentOrder();
        return null;
    }

    static AnswerCityRegister checkCityRegister(StudentOrder so) {
        CityRegisterValidator crv = new CityRegisterValidator();
        return crv.checkCityRegister(so);
    }

    static AnswerWedding checkWedding(StudentOrder so) {
        WeddingValidator wv = new WeddingValidator();
        return wv.checkWedding(so);
    }

    static AnswerChildren checkChildren(StudentOrder so) {
        ChildrenValidator cv = new ChildrenValidator();
        return cv.checkChildren(so);
    }

    static AnswerStudent checkStudent(StudentOrder so) {
        StudentValidator sv = new StudentValidator();
        return sv.checkStudent(so);
    }

    static void sendMail (StudentOrder so){
            new MailSender().sendMail(so);
    }

}
