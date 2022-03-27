package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.DBFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.api.MemoryFactoryStorage;

public class FactoryStorage implements IFactoryStorage{

    private static final FactoryStorage instance = new FactoryStorage();

    private MemoryFactoryStorage memoryFactoryStorage;
    private HibernateFactoryStorage hibernateFactoryStorage;
    private DBFactoryStorage dbFactoryStorage;

    public FactoryStorage() {
        this.memoryFactoryStorage=MemoryFactoryStorage.getInstance();
        this.hibernateFactoryStorage =HibernateFactoryStorage.getInstance();
        this.dbFactoryStorage=DBFactoryStorage.getInstance();
    }

    @Override
    public IUserStorage getUserStorage() {
        return this.hibernateFactoryStorage.getUserStorage();
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return this.hibernateFactoryStorage.getMessageStorage();
    }

    @Override
    public IAuditStorage getAuditStorage() {
        return this.hibernateFactoryStorage.getAuditStorage();
    }

    public static FactoryStorage getInstance() {
        return instance;
    }

}
