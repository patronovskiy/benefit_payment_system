package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.AnswerChildren;
import edu.patronovskiy.studentorder.domain.StudentOrder;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class ChildrenValidator {
    //проверка детей

    public AnswerChildren checkChildren(StudentOrder so) {
        AnswerChildren ans = new AnswerChildren();
        System.out.println("Children checked");
        return ans;
    }
}
