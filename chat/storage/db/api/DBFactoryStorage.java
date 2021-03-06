package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.StorageAuditDB;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.StorageMessageDB;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.StorageUserDB;


public class DBFactoryStorage implements IFactoryStorage {


    private static final DBFactoryStorage instance = new DBFactoryStorage();


    private IUserStorage userStorage;
    private IMessageStorage messageStorage;
    private IAuditStorage auditStorage;


    public DBFactoryStorage() {

        this.userStorage= StorageUserDB.getInstance();
        this.messageStorage= StorageMessageDB.getInstance();
        this.auditStorage = StorageAuditDB.getInstance();

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

    public static DBFactoryStorage getInstance() {
        return instance;
    }
}


