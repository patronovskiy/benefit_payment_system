package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.CityRegisterCheckerResponse;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.exception.CityRegisterException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class RealCityRegisterChecker implements CityRegisterChecker{
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        return null;    //TODO
    }
}
