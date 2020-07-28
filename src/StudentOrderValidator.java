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

        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding wedAnswer = checkWedding(so);
        AnswerChildren childAnswer = checkChildren(so);
        AnswerStudent studentAnswer = checkStudent(so);
        sendMail(so);
    }

    static StudentOrder readStudentOrder() {
        return new StudentOrder();
    }

    //проверка заявителя в реестре населения
    static AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();
        return  ans;
    }

    //проверка брака в реестре ЗАГС
    static AnswerWedding checkWedding(StudentOrder so) {
        AnswerWedding ans = new AnswerWedding();
        return ans;
    }

    //проверка детей
    static AnswerChildren checkChildren(StudentOrder so) {
        AnswerChildren ans = new AnswerChildren();
        return ans;
    }

    //
    static AnswerStudent checkStudent(StudentOrder so) {
        AnswerStudent ans = new AnswerStudent();
        return ans;
    }

    private static void sendMail(StudentOrder so) {
    }
}
