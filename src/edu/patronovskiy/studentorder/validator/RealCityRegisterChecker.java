package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.register.CityRegisterResponse;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.exception.CityRegisterException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class RealCityRegisterChecker implements CityRegisterChecker{
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        return null;    //TODO
    }
}
