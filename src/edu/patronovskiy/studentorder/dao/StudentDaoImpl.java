package edu.patronovskiy.studentorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import edu.patronovskiy.studentorder.config.Config;
import edu.patronovskiy.studentorder.domain.Address;
import edu.patronovskiy.studentorder.domain.Adult;
import edu.patronovskiy.studentorder.domain.Child;
import edu.patronovskiy.studentorder.domain.PassportOffice;
import edu.patronovskiy.studentorder.domain.Person;
import edu.patronovskiy.studentorder.domain.RegisterOffice;
import edu.patronovskiy.studentorder.domain.Street;
import edu.patronovskiy.studentorder.domain.StudentOrder;
import edu.patronovskiy.studentorder.domain.StudentOrderStatus;
import edu.patronovskiy.studentorder.domain.University;
import edu.patronovskiy.studentorder.exception.DaoException;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class StudentDaoImpl implements StudentOrderDao {

    //запросы в БД
    private static final String INSERT_ORDER =
        "INSERT INTO jc_student_order(" +
            "student_order_status, student_order_date, " +
            "h_sur_name, h_name, h_patronymic, h_date_of_birth, " +
            "h_passport_seria, h_passport_number, " +
            "h_passport_date, h_passport_office_id, " +
            "h_post_index, h_street_code, h_building, h_extension, h_appartment, " +
            "h_university_id, h_student_number," +
            "w_sur_name, w_name, w_patronymic, w_date_of_birth, " +
            "w_passport_seria, w_passport_number, " +
            "w_passport_date, w_passport_office_id, " +
            "w_post_index, w_street_code, w_building, w_extension, w_appartment, " +
            "w_university_id, w_student_number," +
            "certificate_id, register_office_id, marriage_date)" +
            "VALUES (?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, " +
            "?, ?, " +
            "?, ?, ?, ?, ?, " +
            "?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, " +
            "?, ?, " +
            "?, ?, ?, ?, ?, " +
            "?, ?, " +
            "?, ?, ?);";

    private static final String INSERT_CHILD =
        "INSERT INTO jc_student_child(" +
        "student_order_id, c_sur_name, c_name, c_patronymic, " +
        "c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, " +
        "c_post_index, c_street_code, c_building, c_extension, c_appartment)" +
        "VALUES (?, ?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, ?, ?, ?);";

    private static final String SELECT_ORDERS =
        "SELECT so.*, " +
        "ro.r_office_area_id, ro.r_office_name, " +
        "po_h.p_office_area_id as h_p_office_area_id, po_h.p_office_name as h_p_office_name, " +
        "po_w.p_office_area_id as w_p_office_area_id, po_w.p_office_name as w_p_office_name " +
        "FROM jc_student_order as so " +
        "INNER JOIN jc_register_office as ro ON ro.r_office_id = so.register_office_id " +
        "INNER JOIN jc_passport_office as po_h ON po_h.p_office_id = so.h_passport_office_id " +
        "INNER JOIN jc_passport_office as po_w ON po_w.p_office_id = so.w_passport_office_id " +
        "WHERE student_order_status = ? ORDER BY student_order_date";

    private static final String SELECT_CHILD =
        "SELECT soc.*, ro.r_office_area_id, ro.r_office_name " +
        "FROM jc_student_child as soc " +
        "INNER JOIN jc_register_office as ro " +
        "ON ro.r_office_id = soc.c_register_office_id " +
        "WHERE soc.student_order_id IN ";

    private static final String SELECT_ORDERS_FULL =
        "SELECT so.*, " +
            "ro.r_office_area_id, ro.r_office_name, " +
            "po_h.p_office_area_id as h_p_office_area_id, " +
            "po_h.p_office_name as h_p_office_name, " +
            "po_w.p_office_area_id as w_p_office_area_id, " +
            "po_w.p_office_name as w_p_office_name, " +
            "soc.*, ro_c.r_office_area_id, ro_c.r_office_name " +
            "FROM jc_student_order as so " +
            "INNER JOIN jc_register_office as ro ON ro.r_office_id = so.register_office_id " +
            "INNER JOIN jc_passport_office as po_h ON po_h.p_office_id = so.h_passport_office_id " +
            "INNER JOIN jc_passport_office as po_w ON po_w.p_office_id = so.w_passport_office_id " +
            "INNER JOIN jc_student_child as soc ON soc.student_order_id = so.student_order_id " +
            "INNER JOIN jc_register_office as ro_c ON soc.c_register_office_id = ro_c.r_office_id " +
            "WHERE student_order_status = ? ORDER BY student_order_date";


    //подключение к БД
    //TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
            Config.getProperty(Config.DB_URL),
            Config.getProperty(Config.DB_LOGIN),
            Config.getProperty(Config.DB_PASSWORD)
        );
        return con;
    }


    //сохранение данных в БД
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
                setParamsForAdult(stmt, 18, so.getWife());

                //Marriage
                stmt.setString(33, so.getMarriageCertificateId());
                stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                stmt.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

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
        stmt.setLong(start + 13, adult.getUniversity().getUniversityId());
        stmt.setString(start + 14, adult.getStudentId());
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


    //получение данных из БД
    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        return getStudentOrdersOneSelect();
        //return getStudentOrdersTwoSelect();
    }


    private List<StudentOrder> getStudentOrdersOneSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS_FULL)) {

            //мапа со студенческими заявками
            Map <Long, StudentOrder> maps = new HashMap<>();

            //устанавливаем требуемый статус обрабатываемых заявок
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            //выполняем запрос и получаем заявки из бд
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Long soId = rs.getLong("student_order_id");
                //проверяем, что этой заявки еще нет в мапе, если нет - создаем
                if (!maps.containsKey(soId)) {
                    StudentOrder so = getFullStudentOrder(rs);

                    result.add(so);
                    maps.put(soId, so);
                }
                StudentOrder so = maps.get(soId);
                so.addChild(fillChild(rs));
            }
            rs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private List<StudentOrder> getStudentOrdersTwoSelect() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)) {

            //устанавливаем требуемый статус обрабатываемых заявок
            stmt.setInt(1, StudentOrderStatus.START.ordinal());
            //выполняем запрос и получаем заявки из бд
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                StudentOrder so = getFullStudentOrder(rs);

                result.add(so);
            }

            findChildren(con, result);

            rs.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private StudentOrder getFullStudentOrder(ResultSet rs) throws SQLException {
        StudentOrder so = new StudentOrder();

        fillStudentOrder(rs, so);
        fillMarriage(rs, so);

        so.setHusband(fillAdult(rs, "h_"));
        so.setWife(fillAdult(rs, "w_"));
        return so;
    }

    private void findChildren(Connection con, List<StudentOrder> result) throws SQLException {
        String orders_ids_clause = "(" + result.stream().map(so -> String.valueOf(so.getStudentOrderId()))
            .collect(Collectors.joining(",")) + ")";

        Map<Long, StudentOrder> maps = result.stream().collect(Collectors
            .toMap(so -> so.getStudentOrderId(), so -> so));

        try(PreparedStatement stmt = con.prepareStatement(SELECT_CHILD + orders_ids_clause)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Child child = fillChild(rs);
                StudentOrder so = maps.get(rs.getLong("student_order_id"));
                so.addChild(child);
            }
        }
    }


    private Adult fillAdult(ResultSet rs, String prefix) throws SQLException {
        Adult adult = new Adult();
        adult.setSurName(rs.getString(prefix + "sur_name"));
        adult.setGivenName(rs.getString(prefix + "name"));
        adult.setPatronymic(rs.getString(prefix + "patronymic"));
        adult.setDateOfBirth(rs.getDate(prefix + "date_of_birth").toLocalDate());
        adult.setPassportSeria(rs.getString(prefix + "passport_seria"));
        adult.setPassportNumber(rs.getString(prefix + "passport_number"));
        adult.setIssueDate(rs.getDate(prefix + "passport_date").toLocalDate());

        Long passportOfficeId = rs.getLong(prefix + "passport_office_id");
        String passportOfficeAreaId = rs.getString(prefix + "p_office_area_id");
        String passportOfficeName = rs.getString(prefix + "p_office_name");
        PassportOffice po = new PassportOffice(passportOfficeId, passportOfficeAreaId, passportOfficeName);
        adult.setIssueDepartment(po);

        Address address = new Address();
        address.setPostCode(rs.getString(prefix + "post_index"));
        Street street = new Street(rs.getLong(prefix + "street_code"), "");
        address.setStreet(street);
        address.setBuilding(rs.getString(prefix + "building"));
        address.setExtension(rs.getString(prefix + "extension"));
        address.setAppartment(rs.getString(prefix + "appartment"));
        adult.setAddress(address);

        University university = new University(rs.getLong(prefix + "university_id"), "");
        adult.setUniversity(university);

        adult.setStudentId(rs.getString(prefix + "student_number"));

        return adult;

    }

    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));
    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());
        String areaId = rs.getString("r_office_area_id");
        String officeName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(rs.getLong("register_office_id"), areaId, officeName);
        so.setMarriageOffice(ro);
    }

    private Child fillChild(ResultSet rs) throws SQLException {
        String surName = rs.getString("c_sur_name");
        String givenName = rs.getString("c_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_date_of_birth").toLocalDate();

        String certificateNumber = rs.getString("c_certificate_number");
        LocalDate issueDate = rs.getDate("c_certificate_date").toLocalDate();

        Long officeId = rs.getLong("c_register_office_id");
        String officeAreaId = rs.getString("r_office_area_id");
        String officeName = rs.getString("r_office_name");
        RegisterOffice issueDepartment = new RegisterOffice(officeId, officeAreaId, officeName);

        Address address = new Address();
        address.setPostCode(rs.getString("c_post_index"));
        Street street = new Street(rs.getLong("c_street_code"), "");
        address.setStreet(street);
        address.setBuilding(rs.getString( "c_building"));
        address.setExtension(rs.getString("c_extension"));
        address.setAppartment(rs.getString("c_appartment"));

        Child child = new Child(surName, givenName, patronymic, dateOfBirth);
        child.setCertificateNumber(certificateNumber);
        child.setIssueDate(issueDate);
        child.setIssueDepartment(issueDepartment);
        child.setAddress(address);

        return child;
    }
}