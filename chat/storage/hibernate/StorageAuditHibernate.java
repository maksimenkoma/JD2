package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate;


import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.AuditHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.IStorageAuditHibernate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StorageAuditHibernate implements IStorageAuditHibernate {

    private static final StorageAuditHibernate instance = new StorageAuditHibernate();

    private HibernateDBInitializer hb;

    public StorageAuditHibernate() {

        this.hb = HibernateDBInitializer.getInstance();
    }

    @Override
    public void saveAuditHibernate(Audit audit) {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(auditChangeAuditHibernate(audit));
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public List<Audit> pullAuditHibernate() {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditHibernate> query = cb.createQuery(AuditHibernate.class);
        Root<AuditHibernate> from = query.from(AuditHibernate.class);
        query.select(from);
        List<AuditHibernate> resultList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        List<Audit> audits = new ArrayList<>(resultList.size());

        for (AuditHibernate auditHibernate : resultList) {

            audits.add(auditHibernateChangeAudit(auditHibernate));
        }
        return audits;

    }

    public AuditHibernate auditChangeAuditHibernate(Audit audit) {

        AuditHibernate auditHibernate = new AuditHibernate();
        auditHibernate.setAuthor(audit.getAuthor());
        auditHibernate.setText(audit.getText());
        auditHibernate.setAuthor(audit.getAuthor());
        auditHibernate.setDt_create(audit.getDt_create());

        return auditHibernate;
    }

    public Audit auditHibernateChangeAudit(AuditHibernate auditHibernate) {

        Audit audit = new Audit();
        audit.setId(auditHibernate.getId());
        audit.setAuthor(auditHibernate.getAuthor());
        audit.setText(auditHibernate.getText());
        audit.setDt_create(auditHibernate.getDt_create());
        return audit;
    }

    public static StorageAuditHibernate getInstance() {
        return instance;
    }
}
