package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.AnswerCityRegister;
import edu.patronovskiy.studentorder.domain.StudentOrder;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class CityRegisterValidator {

    String hostName;
    int port;

    String login;
    String password;

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        //TODO заглушка
        personChecker = new FakeCityRegisterChecker();
    }

    //проверка заявителя в реестре населения
    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        personChecker.checkPerson(so.getHusband());
        personChecker.checkPerson(so.getWife());
        personChecker.checkPerson(so.getChild());

        AnswerCityRegister ans = new AnswerCityRegister();
        return  ans;
    }
}
