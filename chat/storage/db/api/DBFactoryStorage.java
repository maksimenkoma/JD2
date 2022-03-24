package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.AuditUsersDB;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.StorageDB;

public class DBFactoryStorage implements IDBStorage {

    private static final DBFactoryStorage instance = new DBFactoryStorage();

    @Override
    public IAuditUsersDB getAuditUserDB() {
        return AuditUsersDB.getInstance();
    }

    @Override
    public IStorageDB getStorageDB() {
        return StorageDB.getInstance();
    }

    public static DBFactoryStorage getInstance() {
        return instance;
    }

}
