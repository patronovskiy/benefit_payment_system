package edu.patronovskiy.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import edu.patronovskiy.studentorder.config.Config;
import edu.patronovskiy.studentorder.domain.Address;
import edu.patronovskiy.studentorder.domain.Adult;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.domain.StudentOrderStatus;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class StudentDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER =
        "INSERT INTO jc_student_order(" +
            "student_order_status, student_order_date, " +
            "h_sur_name, h_name, h_patronymic, h_date_of_birth, " +
            "h_passport_seria, h_passport_number, " +
            "h_passport_date, h_passport_office_id, " +
            "h_post_index, h_street_code, h_building, h_extension, h_appartment, " +
            "w_sur_name, w_name, w_patronymic, w_date_of_birth, " +
            "w_passport_seria, w_passport_number, w_passport_date, " +
            "w_passport_office_id, " +
            "w_post_index, w_street_code, w_building, w_extension, w_appartment, " +
            "certificate_id, register_office_id, marriage_date)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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
        long result = -1L;

        try (Connection con = getConnection();
             //делаем запрос в бд и сразу оттуда сохраняем в массив данные из колонки student_order_id
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"})) {

            //Header
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            //Husband and wife
            setParamsForAdult(stmt, 3, so.getHusband());
            setParamsForAdult(stmt, 16, so.getWife());

            //Marriage
            stmt.setString(29, so.getMarriageCertificateId());
            stmt.setLong(30, so.getMarriageOffice().getOfficeId());
            stmt.setDate(31, java.sql.Date.valueOf(so.getMarriageDate()));

            stmt.executeUpdate();

            //возвращает данные из бд
            ResultSet gKRs = stmt.getGeneratedKeys();
            if(gKRs.next()) {
                result = gKRs.getLong(1);
            }
            gKRs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;  //return id of student order
    }

    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult)
        throws SQLException {
        stmt.setString(start, adult.getSurName());
        stmt.setString(start + 1, adult.getGivenName());
        stmt.setString(start + 2, adult.getPatronymic());
        stmt.setDate(start + 3, java.sql.Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, java.sql.Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        Address address = adult.getAddress();
        stmt.setString(start + 8, address.getPostCode());
        stmt.setLong(start + 9, address.getStreet().getStreetCode());
        stmt.setString(start + 10, address.getBuilding());
        stmt.setString(start + 11, address.getExtension());
        stmt.setString(start + 12, address.getAppartment());
    }
}
