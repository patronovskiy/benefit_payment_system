package edu.patronovskiy.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import edu.patronovskiy.studentorder.config.Config;
import edu.patronovskiy.studentorder.domain.Street;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//DAO - Data access object
public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street where UPPER(street_name) like UPPER(?)";

    private Connection getConnection() throws SQLException {
        //регистрация драйвера в подсистеме jdbc, необязательно с версии спецификации 4.0
        //Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
            Config.getProperty(Config.DB_URL),
            Config.getProperty(Config.DB_LOGIN),
            Config.getProperty(Config.DB_PASSWORD));    //todo установить эти параметры
        return con;
    }

    public List<Street> findStreets(String pattern) throws DaoException {    //todo обработка ошибки
        List<Street> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                result.add(new Street(rs.getLong("street_code"), rs.getString("street_name")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return result;
    }
}
