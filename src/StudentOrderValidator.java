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
            AnswerCityRegister cityAnswer = CityRegisterValidator.checkCityRegister(so);
            if (!cityAnswer.success) {
                continue;
            }
            AnswerWedding wedAnswer = WeddingValidator.checkWedding(so);
            AnswerChildren childAnswer = ChildrenValidator.checkChildren(so);
            AnswerStudent studentAnswer = StudentValidator.checkStudent(so);
            sendMail(so);
        }
    }

    static StudentOrder readStudentOrder() {
        new StudentOrder();
        return null;
    }

    private static void sendMail(StudentOrder so) {
    }
}
