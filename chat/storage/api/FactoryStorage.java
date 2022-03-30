package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api.DBFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.memory.api.MemoryFactoryStorage;

public class FactoryStorage implements IFactoryStorage {

    private static final FactoryStorage instance = new FactoryStorage();

    private MemoryFactoryStorage memoryFactoryStorage;
    private HibernateFactoryStorage hibernateFactoryStorage;
    private DBFactoryStorage dbFactoryStorage;
    private String choice;

    public FactoryStorage() {
        this.memoryFactoryStorage = MemoryFactoryStorage.getInstance();
        this.hibernateFactoryStorage = HibernateFactoryStorage.getInstance();
        this.dbFactoryStorage = DBFactoryStorage.getInstance();
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public IUserStorage getUserStorage() {

        IUserStorage userStorage;

        switch (choice) {

            case ("hibernate"):
                userStorage = hibernateFactoryStorage.getUserStorage();
                break;
            case ("memory"):
                userStorage = memoryFactoryStorage.getUserStorage();
                break;

            case ("db"):
                userStorage = dbFactoryStorage.getUserStorage();
                break;
            default:
                throw new IllegalStateException("не передан обязательный аргумент");
        }

        return userStorage;
    }

    @Override
    public IMessageStorage getMessageStorage() {

        IMessageStorage messageStorage;

        switch (choice) {

            case ("hibernate"):
                messageStorage = hibernateFactoryStorage.getMessageStorage();
                break;
            case ("memory"):
                messageStorage = memoryFactoryStorage.getMessageStorage();
                break;

            case ("db"):
                messageStorage = dbFactoryStorage.getMessageStorage();
                break;
            default:
                throw new IllegalStateException("не передан обязательный аргумент");
        }

        return messageStorage;
    }


    @Override
    public IAuditStorage getAuditStorage() {

        IAuditStorage auditStorage;

        switch (choice) {

            case ("hibernate"):
                auditStorage = hibernateFactoryStorage.getAuditStorage();
                break;
            case ("memory"):
                auditStorage = memoryFactoryStorage.getAuditStorage();
                break;

            case ("db"):
                auditStorage = dbFactoryStorage.getAuditStorage();
                break;
            default:
                throw new IllegalStateException("не передан обязательный аргумент");
        }

        return auditStorage;
    }

    public static FactoryStorage getInstance() {
        return instance;
    }


}
