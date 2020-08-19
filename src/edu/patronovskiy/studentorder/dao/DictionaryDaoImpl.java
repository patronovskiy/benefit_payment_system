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
public class DictionaryDaoImpl implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name FROM jc_street " +
        "where UPPER(street_name) like UPPER(?)";

    private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office where p_office_area_id = ?";

    private static final String GET_REGISTER = "SELECT * FROM jc_register_office where r_office_area_id = ?";

    private static final String GET_AREA = "SELECT * FROM jc_country_struct WHERE area_id like ? and area_id <> ?";

    //TODO refactoring - make one method
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

    @Override
    public List<PassportOffice> findPassportOffices(final String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                result.add(new PassportOffice(
                    rs.getLong("p_office_id"),
                    rs.getString("p_office_area_id"),
                    rs.getString("p_office_name")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(final String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER)) {

            stmt.setString(1, areaId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                result.add(new RegisterOffice(
                    rs.getLong("r_office_id"),
                    rs.getString("r_office_area_id"),
                    rs.getString("r_office_name")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<CountryArea> findAreas(final String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_AREA)) {

            String param1 = buildParam(areaId);
            String param2 = areaId;

            stmt.setString(1, param1);
            stmt.setString(2, param2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                result.add(new CountryArea(
                    rs.getString("area_id"),
                    rs.getString("area_name")));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private String buildParam(final String areaId) throws SQLException{
        if(areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0, 2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0, 5) + "___0000";
        } else if (areaId.endsWith("0000")){
            return areaId.substring(0, 8) + "____";
        }
        throw new SQLException("Invalid parameter 'areaId:' " + areaId);
    }
}
