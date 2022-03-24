package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageAuditHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageMessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageUserHibernate;

public class HibernateFactoryStorage implements IHibernateStorage{

    private static final HibernateFactoryStorage instance = new HibernateFactoryStorage();

    @Override
    public IStorageUserHibernate getStorageUserHibernate() {
        return StorageUserHibernate.getInstance();
    }

    @Override
    public IStorageMessageHibernate getStorageMessageHibernate() {
        return StorageMessageHibernate.getInstance();
    }

    @Override
    public IStorageAuditHibernate getStorageAuditHibernate() {
        return StorageAuditHibernate.getInstance();
    }

    public static HibernateFactoryStorage getInstance() {
        return instance;
    }
}
