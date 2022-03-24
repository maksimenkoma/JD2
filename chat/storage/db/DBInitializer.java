package by.it_academy.jd2.m_jd2_88_22.chat.storage.db;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class DBInitializer {

    private volatile static DBInitializer instance;

    private ComboPooledDataSource cpds;

    private DBInitializer() throws IOException, SQLException,PropertyVetoException {

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/messenger?ApplicationName=TestSweetApp");
        cpds.setUser("postgres");
        cpds.setPassword("1");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public DataSource getDataSource() {
        return cpds;
    }


    public static DBInitializer getInstance() {
        if (instance == null) {
            synchronized (DBInitializer.class) {
                if (instance == null) {
                    try {
                        instance = new DBInitializer();
                    } catch (Exception e) {
                        throw new RuntimeException("Ошибка подключения к базе", e);
                    }

                }
            }
        }
        return instance;
    }
}