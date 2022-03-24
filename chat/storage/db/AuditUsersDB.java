package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.IAuditUsersDB;
import by.it_academy.jd2.m_jd2_88_22.demo.model.Pageable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditUsersDB implements IAuditUsersDB {

    private static final AuditUsersDB instance = new AuditUsersDB();
    private final DataSource dataSource;


    public AuditUsersDB() {

        dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public Long create(Audit audit) {
        if(audit == null){
            throw new IllegalArgumentException("Аудит не может быть null");
        }

        String sql = "INSERT INTO app.audit_users(text, author, dt_create)\n" +
                     "\tVALUES (?, ?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
        ) {
            conn.setAutoCommit(false);

            ps.setObject(1, audit.getText());
            ps.setObject(2, audit.getAuthor() != null ? audit.getAuthor() : null);
            ps.setObject(3, audit.getDt_create());

            ps.executeUpdate();

            conn.commit();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
    }


    @Override
    public List<Audit> read(Pageable pageable) {

        Integer limit = null;
        Integer offset = null;

        if(pageable != null){
            if(pageable.getSize() > 0){
                limit = pageable.getSize();
            }

            if(limit != null && pageable.getPage() > 0){
                offset = (pageable.getPage() - 1) * limit;
            }
        }

        List<Audit> data = new ArrayList<>();

        String sql = "SELECT audit.id, \n" +
                     "\t   audit.dt_create,\n" +
                     "\t   audit.text,\n" +
                     "\t   \n" +
                     "\t   obj.dt_reg as obj_dt_reg, \n" +
                     "\t   obj.fio as obj_fio, \n" +
                     "\t   obj.birthday as obj_birthday,\n" +
                     "\t   \n" +
                     "\t   audit.author,\n" +
                     "\t   \n" +
                     "\t   author.dt_reg as author_dt_reg, \n" +
                     "\t   author.fio as author_fio, \n" +
                     "\t   author.birthday as author_birthday\n" +
                     "FROM app.audit_users as audit \n" +
                     "LEFT JOIN app.users as author ON audit.author = author.login\n" +
                     "LEFT JOIN app.users as obj ON audit.user = obj.login;";

        if(limit != null){
            sql += "\n LIMIT " + limit;
        }
        if (offset != null){
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {

            int index = 1;
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Long id = rs.getLong("id");
                    LocalDateTime dtCreate = rs.getObject("dt_create", LocalDateTime.class);
                    String text = rs.getString("text");
                  //  User user = User.Builder.createBuilder()
                    //        .setLogin(rs.getString("user"))
                          //  .setFio(rs.getString("obj_fio"))
                           // .setBirthday(rs.getObject("obj_dt_birthday", LocalDate.class))
                        //    .setRegistration(rs.getObject("obj_dt_reg", LocalDateTime.class))
                        //    .build();
                   // User author = User.Builder.createBuilder()
                  //          .setLogin(rs.getString("author"))
                         //   .setFio(rs.getString("author_fio"))
                        //    .setBirthday(rs.getObject("author_dt_birthday", LocalDate.class))
                       //     .setRegistration(rs.getObject("author_dt_reg", LocalDateTime.class))
                       //     .build();
                  //  Audit audit = new Audit(id, dtCreate, text, user, author);
                 //   data.add(audit);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
        return data;

    }


    public static AuditUsersDB getInstance() {
        return instance;
    }
}
