package edu.patronovskiy.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import edu.patronovskiy.studentorder.config.Config;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class StudentDaoImpl implements StudentOrderDao {

    //TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
            Config.getProperty(Config.DB_URL),
            Config.getProperty(Config.DB_LOGIN),
            Config.getProperty(Config.DB_PASSWORD)
        );
        return con;
    }

    @Override
    public Long saveStudentOrder(final StudentOrder so) throws DaoException {
        return null;
    }
}
