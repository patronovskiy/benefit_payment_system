package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.CityRegisterCheckerResponse;
import edu.patronovskiy.studentorder.domain.Person;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//временный класс-заглушка
public class FakeCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) {
        return null;    //TODO
    }
}
