package edu.patronovskiy.studentorder.validator;

import edu.patronovskiy.studentorder.domain.Adult;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.register.CityRegisterResponse;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.exception.CityRegisterException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//временный класс-заглушка
public class FakeCityRegisterChecker implements CityRegisterChecker {

    private static final String GOOD_1 = "1000";
    private static final String GOOD_2 = "2000";
    private static final String BAD_1 = "1001";
    private static final String BAD_2 = "2001";
    private static final String ERROR_1 = "1002";
    private static final String ERROR_2 = "2002";

    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {
        CityRegisterResponse result = new CityRegisterResponse();

        if (person instanceof Adult) {
            Adult t = (Adult) person;
            if (t.getPassportSeria().equals(GOOD_1) || t.getPassportSeria().equals(GOOD_2)) {
                result.setExisting(true);
                result.setTemporal(false);
            }
            if (t.getPassportSeria().equals(BAD_1) || t.getPassportSeria().equals(BAD_2)) {
                result.setExisting(false);
            }
            if (t.getPassportSeria().equals(ERROR_1) || t.getPassportSeria().equals(ERROR_2)) {
                throw new CityRegisterException("Fake Error");  //todo
            }
        }

        if(person instanceof Child) {
            result.setExisting(true);
            result.setTemporal(true);
        }
        System.out.println(result);
        return result;
    }


}
