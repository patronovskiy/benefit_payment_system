package edu.patronovskiy.studentorder.validator;

import java.util.List;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.domain.register.AnswerCityRegister;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.register.AnswerCityRegisterItem;
import edu.patronovskiy.studentorder.domain.register.CityRegisterResponse;
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

    //проверка семьи-заявителя в реестре населения
    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();

        ans.addItem(checkPerson(so.getHusband()));
        ans.addItem(checkPerson(so.getWife()));
        for (Child child : so.getChildren()) {
           ans.addItem(checkPerson(child));
        }

        return  ans;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        try {
            CityRegisterResponse cans = personChecker.checkPerson(person);
        } catch (CityRegisterException ex) {
            ex.printStackTrace();
        }
        return null;    //todo
    }
}
