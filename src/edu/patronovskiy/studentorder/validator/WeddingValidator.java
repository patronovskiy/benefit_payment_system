package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.AnswerWedding;
import edu.patronovskiy.studentorder.domain.StudentOrder;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class WeddingValidator {
    //проверка брака в реестре ЗАГС

    public AnswerWedding checkWedding(StudentOrder so) {
        AnswerWedding ans = new AnswerWedding();
        System.out.println("Wedding checked");
        return ans;
    }
}
