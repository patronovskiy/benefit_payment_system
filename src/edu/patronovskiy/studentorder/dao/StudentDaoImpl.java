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
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.Person;
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
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_CHILD =
        "INSERT INTO jc_student_child(" +
        "student_order_id, c_sur_name, c_name, c_patronymic, " +
        "c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, " +
        "c_post_index, c_street_code, c_building, c_extension, c_appartment)" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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

            //отключение автоматических транзакций
            con.setAutoCommit(false);
            try {
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
                if (gKRs.next()) {
                    result = gKRs.getLong(1);
                }
                gKRs.close();

                saveChildren(con, so, result);

                con.commit();

            } catch(SQLException ex) {
                con.rollback();
                throw ex;
            }

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;  //return id of student order
    }

    private void saveChildren(Connection con, StudentOrder so, Long soId) throws SQLException {
        try(PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {
            for(Child child : so.getChildren()) {
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.addBatch();    //добавляем команды в пакет - как буферизация
            }
            stmt.executeBatch();    //исполняем команды всего пакета разом.
                                    // возвращает массив с кол-вом задейств строк
        }
    }

    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException {
        setParamsForPerson(stmt, 2, child);
        stmt.setString(6, child.getCertificateNumber());
        stmt.setDate(7, java.sql.Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, 9, child);
    }

    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult)
        throws SQLException {
        setParamsForPerson(stmt, start, adult);
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, java.sql.Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, start + 8, adult);
    }

    private void setParamsForPerson(PreparedStatement stmt, int start, Person person)
        throws SQLException {
        stmt.setString(start, person.getSurName());
        stmt.setString(start + 1, person.getGivenName());
        stmt.setString(start + 2, person.getPatronymic());
        stmt.setDate(start + 3, java.sql.Date.valueOf(person.getDateOfBirth()));
    }

    private void setParamsForAddress(final PreparedStatement stmt, final int start, final Person person)
        throws SQLException {
        Address address = person.getAddress();
        stmt.setString(start, address.getPostCode());
        stmt.setLong(start + 1, address.getStreet().getStreetCode());
        stmt.setString(start + 2, address.getBuilding());
        stmt.setString(start + 3, address.getExtension());
        stmt.setString(start + 4, address.getAppartment());
    }
}