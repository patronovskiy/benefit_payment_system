package edu.patronovskiy.studentorder.validator;

import java.util.List;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.domain.register.AnswerCityRegister;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.register.AnswerCityRegisterItem;
import edu.patronovskiy.studentorder.domain.register.CityRegisterResponse;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.exception.CityRegisterException;
import edu.patronovskiy.studentorder.exception.TransportException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class CityRegisterValidator {

    //код ошибки, когда мы не смогли добраться до ГРН
    public static final String IN_CODE = "NO_GRN";

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
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;
        try {
            CityRegisterResponse tmp = personChecker.checkPerson(person);
            status = tmp.isExisting() ?
                AnswerCityRegisterItem.CityStatus.YES :
                AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
        } catch (TransportException ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());

        } catch (Exception ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        }

        AnswerCityRegisterItem answer = new AnswerCityRegisterItem(status, person, error);

        return answer;    //todo
    }
}
