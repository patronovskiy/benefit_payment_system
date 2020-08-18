package edu.patronovskiy.studentorder.dao;

import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//DAO - Data access object
public interface StudentOrderDao {
    Long saveStudentOrder(StudentOrder so) throws DaoException;
}
