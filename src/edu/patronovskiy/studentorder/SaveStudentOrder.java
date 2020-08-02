package edu.patronovskiy.studentorder;

import edu.patronovskiy.studentorder.domain.Adult;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.StudentOrder;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class SaveStudentOrder {
    public static void main(String[] args) {
        StudentOrder studentOrder = new StudentOrder();


        long ans = saveStudentOrder(studentOrder);

    }

    static long saveStudentOrder(StudentOrder studentOrder) {
        long answer = 0;
        System.out.println("Student order saved");
        return answer;
    }

    static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);

        Adult husband = new Adult("Richard", "Sapogov", "Petrovich", null);

        return so;
    }


}
