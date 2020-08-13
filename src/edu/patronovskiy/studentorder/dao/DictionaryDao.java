package edu.patronovskiy.studentorder.dao;

import java.util.List;
import edu.patronovskiy.studentorder.domain.Street;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
}
