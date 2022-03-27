package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StorageMessageDB implements IMessageStorage {


    private static final StorageMessageDB instance = new StorageMessageDB();

    private final DataSource dataSource;

    public StorageMessageDB() {

        dataSource = DBInitializer.getInstance().getDataSource();
    }


    @Override
    public void saveMessage(Message message, User userActive) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO exam.message_table (recipient, messages, dateTime, fk_message_from)" +
                                                 "VALUES (?,?,?,?)")) {

            preparedStatement.setString(1, message.getRecipient());


            preparedStatement.setString(2, message.getMessage());

            preparedStatement.setTimestamp(3, Timestamp.valueOf(message.getDateTime()));

            preparedStatement.setString(4, userActive.getLogin());

            preparedStatement.addBatch();

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Message> getHistoryMessage(User userActive) {


        return getHistoryMessage(userActive, null);


    }


    @Override
    public List<Message> getHistoryMessage(User userActive, Pageable pageable) {
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
                     "    recipient = " + userActive.getLogin() + "";


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


    public static StorageMessageDB getInstance() {
        return instance;
    }

}
