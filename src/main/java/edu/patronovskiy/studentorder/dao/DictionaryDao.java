package edu.patronovskiy.studentorder.dao;

import java.util.List;
import edu.patronovskiy.studentorder.domain.CountryArea;
import edu.patronovskiy.studentorder.domain.PassportOffice;
import edu.patronovskiy.studentorder.domain.RegisterOffice;
import edu.patronovskiy.studentorder.domain.Street;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//DAO - Data access object
public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffices(String areaId) throws DaoException;
    List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException;
    List<CountryArea> findAreas(String areaId) throws DaoException;
}
