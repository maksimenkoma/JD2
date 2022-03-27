package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StorageAuditDB implements IAuditStorage {

    private static final StorageAuditDB instance = new StorageAuditDB();

    private final DataSource dataSource;

    public StorageAuditDB() {

        dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public void saveAudit(Audit audit) {

        if(audit == null){
            throw new IllegalArgumentException("Аудит не может быть null");
        }

        String sql = "INSERT INTO exam.audit_users(text, author, dt_create)\n" +
                     "\tVALUES (?, ?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            conn.setAutoCommit(false);

            ps.setObject(1, audit.getText());
            ps.setObject(2, audit.getAuthor() != null ? audit.getAuthor() : null);
            ps.setObject(3, audit.getDt_create());

            ps.executeUpdate();

            conn.commit();

               } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
    }



    @Override
    public List<Audit> readAudit() {
        return readAudit(null);
    }




    @Override
    public List<Audit> readAudit(Pageable pageable) {
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
                      User author = User.Builder.builder()
                            .setLogin(rs.getString("author"))
                      .setFirstName(rs.getString("obj_firstname"))
                      .setLastName(rs.getString("obj_lastname"))
                      .setMiddleName(rs.getString("obj_middlename"))
                     .setDateBirth(rs.getObject("obj_datebirth", LocalDate.class))
                        .build();
                      Audit audit = new Audit(id,text,author, dtCreate);
                       data.add(audit);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнение SQL", e);
        }
        return data;
    }


    public static StorageAuditDB getInstance() {
        return instance;
    }

}
