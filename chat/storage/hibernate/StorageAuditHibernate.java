package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate;


import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.AuditHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.MessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.UserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StorageAuditHibernate implements IAuditStorage {

    private static final StorageAuditHibernate instance = new StorageAuditHibernate();

    private HibernateDBInitializer hb;

    public StorageAuditHibernate() {

        this.hb = HibernateDBInitializer.getInstance();
    }

    @Override
    public void saveAudit(Audit audit) {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(auditChangeAuditHibernate(audit));
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<Audit> readAudit() {
        return readAudit(null);
    }

    @Override
    public List<Audit> readAudit(Pageable pageable) {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditHibernate> query = cb.createQuery(AuditHibernate.class);
        Root<AuditHibernate> from = query.from(AuditHibernate.class);
        query.select(from);
        TypedQuery<AuditHibernate> qr = entityManager.createQuery(query);

        if (pageable != null && pageable.getPage() > 0) {
            qr.setFirstResult((pageable.getPage() - 1));

        }

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                qr.setMaxResults(pageable.getSize());
            }
        }
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

        auditHibernate.setId(audit.getId());
        auditHibernate.setText(audit.getText());

        auditHibernate.setAuthor(new UserHibernate(audit.getAuthor().getLogin(), audit.getAuthor().getPassword(),
                audit.getAuthor().getFirstName(), audit.getAuthor().getLastName(), audit.getAuthor().getMiddleName(),
                audit.getAuthor().getDateBirth()));

        auditHibernate.setDt_create(audit.getDt_create());

        return auditHibernate;
    }


    public Audit auditHibernateChangeAudit(AuditHibernate auditHibernate) {

        Audit audit = new Audit();
        audit.setId(auditHibernate.getId());
        audit.setAuthor(new User(auditHibernate.getAuthor().getLogin(),
                auditHibernate.getAuthor().getPassword(),
                auditHibernate.getAuthor().getFirstName(), auditHibernate.getAuthor().getLastName(),
                auditHibernate.getAuthor().getMiddleName(), auditHibernate.getAuthor().getDateBirth()));
        audit.setText(auditHibernate.getText());
        audit.setDt_create(auditHibernate.getDt_create());
        return audit;
    }

    public static StorageAuditHibernate getInstance() {
        return instance;
    }
}
