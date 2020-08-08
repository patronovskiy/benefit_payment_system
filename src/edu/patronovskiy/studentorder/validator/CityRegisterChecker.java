package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.register.CityRegisterResponse;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.exception.CityRegisterException;
import edu.patronovskiy.studentorder.exception.TransportException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public interface CityRegisterChecker {
    CityRegisterResponse checkPerson(Person person)
        throws CityRegisterException, TransportException;
}
