package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class HibernateDBInitializer implements AutoCloseable {

    private volatile static HibernateDBInitializer instance;

    private EntityManagerFactory entityManagerFactory;

    private HibernateDBInitializer() throws

            IOException, SQLException, PropertyVetoException {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    public EntityManager getManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public static HibernateDBInitializer getInstance() {
        if (instance == null) {
            synchronized (HibernateDBInitializer.class) {
                if (instance == null) {
                    try {
                        instance = new HibernateDBInitializer();
                    } catch (Exception e) {
                        throw new RuntimeException("Ошибка подключения к базе", e);
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void close() throws Exception {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
