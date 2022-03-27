package by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.StorageAuditMemory;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.StorageMessageMemory;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.StorageUserMemory;

public class MemoryFactoryStorage implements IFactoryStorage {

    private static final MemoryFactoryStorage instance = new MemoryFactoryStorage();

    private IUserStorage userStorage;
    private IMessageStorage messageStorage;
    private IAuditStorage auditStorage;

    public MemoryFactoryStorage() {
        this.userStorage= StorageUserMemory.getInstance();
        this.messageStorage= StorageMessageMemory.getInstance();
        this.auditStorage = StorageAuditMemory.getInstance();

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

    public static MemoryFactoryStorage getInstance() {
        return instance;
    }

}
