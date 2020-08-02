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
    //проверка заявителя в реестре населения

    public AnswerCityRegister checkCityRegister(StudentOrder so) {


        AnswerCityRegister ans = new AnswerCityRegister();
        return  ans;
    }
}
