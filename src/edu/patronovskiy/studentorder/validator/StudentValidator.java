package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.AnswerStudent;
import edu.patronovskiy.studentorder.domain.StudentOrder;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class StudentValidator {
    //проверка, являются ли заявители студентами
    public AnswerStudent checkStudent(StudentOrder so) {
        AnswerStudent ans = new AnswerStudent();
        System.out.println("Students checked");
        return ans;
    }
}
