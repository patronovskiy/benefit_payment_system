package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.CityRegisterCheckerResponse;
import edu.patronovskiy.studentorder.domain.Person;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson(Person person);;
}
