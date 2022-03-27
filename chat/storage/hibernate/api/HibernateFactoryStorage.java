package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageAuditHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageMessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageUserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.StorageAuditMemory;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.StorageMessageMemory;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.StorageUserMemory;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.api.MemoryFactoryStorage;

public class HibernateFactoryStorage implements IFactoryStorage {


    private static final HibernateFactoryStorage instance = new HibernateFactoryStorage();

    private IUserStorage userStorage;
    private IMessageStorage messageStorage;
    private IAuditStorage auditStorage;


    public HibernateFactoryStorage() {

        this.userStorage= StorageUserHibernate.getInstance();
        this.messageStorage= StorageMessageHibernate.getInstance();
        this.auditStorage = StorageAuditHibernate.getInstance();

    }

    @Override
    public IUserStorage getUserStorage() {
        return this.userStorage;
    }

    @Override
    public IMessageStorage getMessageStorage() {
        return this.messageStorage;
    }

    @Override
    public IAuditStorage getAuditStorage() {
        return this.auditStorage;
    }

    public static HibernateFactoryStorage getInstance() {
        return instance;
    }
}
