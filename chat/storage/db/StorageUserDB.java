package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

public class StorageUserDB implements IUserStorage {


    private static final StorageUserDB instance = new StorageUserDB();

    private final DataSource dataSource;

    public StorageUserDB() {

        dataSource = DBInitializer.getInstance().getDataSource();
    }



    @Override
    public User checkUser(String login, String password) {

        User user = new User();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT\n" +
                                                   "    *\n" +
                                                   "FROM\n" +
                                                   "    exam.user_table\n" +
                                                   "WHERE\n" +
                                                   "    login = " + login +"AND password ="+password +" ;");

        ) {
            while (rs.next()) {

                user.setLogin(rs.getString(1));
                user.setLogin(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setMiddleName(rs.getString(5));
                String dateTime = rs.getString(6);
                user.setDateBirth(LocalDate.parse(dateTime));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка выполнения sql");
        }

        return user;
    }


    @Override
    public void saveUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO exam.user_table (login,password,firstName,lastName,middleName, dateBirth)" +
                                                 "VALUES (?,?,?,?,?,?); " +
                                                 " INSERT INTO exam.audit_users(" +
                                                 " text, author, dt_create) " +
                                                 " VALUES ( ?, ?, now()); "
                     )) {

            // connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getLogin());

            preparedStatement.setString(2, user.getPassword());

            preparedStatement.setString(3, user.getFirstName());

            preparedStatement.setString(4, user.getLastName());

            preparedStatement.setString(5, user.getMiddleName());

            preparedStatement.setObject(6, user.getDateBirth());


            preparedStatement.setString(7, "Регистрация");

            preparedStatement.setString(8, user.getLogin());


            preparedStatement.addBatch();

            preparedStatement.executeBatch();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUser(User userRaw) {
        User user = new User();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT\n" +
                                                   "    *\n" +
                                                   "FROM\n" +
                                                   "    exam.user_table\n" +
                                                   "WHERE\n" +
                                                   "    login = " + userRaw.getLogin() + ";");

        ) {
            while (rs.next()) {

                user.setLogin(rs.getString(1));
                user.setLogin(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setMiddleName(rs.getString(5));
                String dateTime = rs.getString(6);
                user.setDateBirth(LocalDate.parse(dateTime));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка выполнения sql");
        }

        return user;
    }


    public static StorageUserDB getInstance() {
        return instance;
    }

}
