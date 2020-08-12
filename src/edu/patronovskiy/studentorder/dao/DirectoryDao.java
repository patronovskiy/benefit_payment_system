package edu.patronovskiy.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import edu.patronovskiy.studentorder.domain.Street;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//DAO - Data access object
public class DirectoryDao {

    private Connection getConnection() throws SQLException {
        //регистрация драйвера в подсистеме jdbc, необязательно с версии спецификации 4.0
        //Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/jc_student",
            "postgres", "Postgresql72");    //todo установить эти параметры
        return con;
    }

    public List<Street> findStreets(String pattern) throws Exception {    //todo обработка ошибки
        List<Street> result = new LinkedList<>();
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        String sql = "SELECT street_code, street_name FROM jc_street where UPPER(street_name) like UPPER('%" + pattern +"%')";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
          result.add(new Street(rs.getLong("street_code"), rs.getString("street_name")));
        }
        return result;
    }
}
