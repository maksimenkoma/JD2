package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.IDBStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.IHibernateStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.api.IStorageFactory;

public interface IFactoryStorage {

    IDBStorage getIDBStorage();

    IHibernateStorage getHibernateStorage();

    IStorageFactory getStorageFactory();

}
