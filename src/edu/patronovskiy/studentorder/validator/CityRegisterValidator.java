package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.AnswerCityRegister;
import edu.patronovskiy.studentorder.domain.CityRegisterCheckerResponse;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.exception.CityRegisterException;

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
        try {
            CityRegisterCheckerResponse hans = personChecker.checkPerson(so.getHusband());
            CityRegisterCheckerResponse wans = personChecker.checkPerson(so.getWife());
            CityRegisterCheckerResponse cans = personChecker.checkPerson(so.getChild());
        } catch (CityRegisterException ex) {
            ex.printStackTrace();
        }

        AnswerCityRegister ans = new AnswerCityRegister();
        return  ans;
    }
}
