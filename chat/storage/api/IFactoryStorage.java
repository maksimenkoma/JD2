package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

public interface IFactoryStorage {

    IUserStorage getUserStorage();

    IMessageStorage getMessageStorage();

    IAuditStorage getAuditStorage();


}
