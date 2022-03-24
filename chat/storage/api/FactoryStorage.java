package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.DBFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.IDBStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.IHibernateStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.api.IStorageFactory;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.api.StorageFactory;

public class FactoryStorage implements IFactoryStorage{

    private static final FactoryStorage instance = new FactoryStorage();

    @Override
    public IDBStorage getIDBStorage() {
        return DBFactoryStorage.getInstance();
    }

    @Override
    public IHibernateStorage getHibernateStorage() {
        return HibernateFactoryStorage.getInstance();
    }

    @Override
    public IStorageFactory getStorageFactory() {
        return StorageFactory.getInstance();
    }

    public static FactoryStorage getInstance() {
        return instance;
    }

}
