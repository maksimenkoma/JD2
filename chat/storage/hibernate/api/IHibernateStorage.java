package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

public interface IHibernateStorage {

    IStorageUserHibernate getStorageUserHibernate();

    IStorageMessageHibernate getStorageMessageHibernate();

    IStorageAuditHibernate getStorageAuditHibernate();

}
