package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.IStorageDB;
import by.it_academy.jd2.m_jd2_88_22.demo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StorageDB implements IStorageDB {

    private static final StorageDB instance = new StorageDB();
    private final DataSource dataSource;

    public StorageDB() {

        dataSource = DBInitializer.getInstance().getDataSource();
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

    @Override
    public User saveUser(User user) {

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
        return user;

    }

    @Override
    public void saveMessage(Message message, User activeUser) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO exam.message_table (recipient, messages, dateTime, fk_message_from)" +
                                                 "VALUES (?,?,?,?)")) {

            preparedStatement.setString(1, message.getRecipient());


            preparedStatement.setString(2, message.getMessage());

            preparedStatement.setTimestamp(3, Timestamp.valueOf(message.getDateTime()));

            preparedStatement.setString(4, activeUser.getLogin());

            preparedStatement.addBatch();

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Message> getHistoryMessage(User activeUser) {
        return getHistoryMessage(activeUser, null);
    }

    @Override
    public List<Message> getHistoryMessage(User activeUser, Pageable pageable) {

        Integer limit = null;
        Integer offset = null;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }
            if (limit != null && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }

        List<Message> messages = new ArrayList<>();

        String sql = "SELECT\n" +
                     "    *\n" +
                     "FROM\n" +
                     "    exam.message_table\n" +
                     "WHERE\n" +
                     "    recipient = " + activeUser.getLogin() + "";


        if (limit != null) {

            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += " ;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            {

                while (rs.next()) {
                    String sender = rs.getString(1);
                    String message = rs.getString(2);
                    LocalDateTime dateTime = rs.getTimestamp(3).toLocalDateTime();

                    Message mess = new Message();
                    mess.setRecipient(sender);
                    mess.setMessage(message);
                    mess.setDateTime(dateTime);
                    messages.add(mess);

                }
            }

        } catch (
                SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
        return messages;
    }

    public static StorageDB getInstance() {
        return instance;
    }


}
