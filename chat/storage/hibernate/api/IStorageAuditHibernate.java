package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.AuditHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.UserHibernate;

import java.util.List;

public interface IStorageAuditHibernate {

    void saveAuditHibernate(Audit audit);

    List<Audit> pullAuditHibernate();

}
