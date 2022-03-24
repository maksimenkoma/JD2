package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.UserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.IStorageUserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StorageUserHibernate implements IStorageUserHibernate {

    private static final StorageUserHibernate instance = new StorageUserHibernate();

    private HibernateDBInitializer hb;


    public StorageUserHibernate() {

        this.hb = HibernateDBInitializer.getInstance();
    }


    @Override
    public void saveUserHibernate(User user) {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(UserChangeUserHibernate(user));
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<User> pullUserHibernate() {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserHibernate> query = cb.createQuery(UserHibernate.class);
        Root<UserHibernate> from = query.from(UserHibernate.class);
        query.select(from);
        List<UserHibernate> resultList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        List<User> users = new ArrayList<>(resultList.size());

        for (UserHibernate userHibernate : resultList) {

            users.add(UserHibernateChangeUser(userHibernate));
        }

        return users;
    }


    @Override
    public List<User> pullUserHibernate(User user) {

        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserHibernate> query = cb.createQuery(UserHibernate.class);
        Root<UserHibernate> from = query.from(UserHibernate.class);
        query.select(from);
        query.where(from.get("login").in(user.getLogin()));

        List<UserHibernate> resultList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        List<User> users = new ArrayList<>(resultList.size());

        for (UserHibernate userHibernate : resultList) {

            users.add(UserHibernateChangeUser(userHibernate));
        }

        return users;
    }


    @Override
    public User pullUserHibernate(String login, String password) {
        User user = new User();
        EntityManager entityManager = hb.getManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserHibernate> query = cb.createQuery(UserHibernate.class);
        Root<UserHibernate> from = query.from(UserHibernate.class);
        query.select(from);
        query.where(from.get("login").in(login), from.get("password").in(password));

        List<UserHibernate> resultList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        for (UserHibernate userHibernate : resultList) {

            user = UserHibernateChangeUser(userHibernate);

        }
        return user;
    }

    public User UserHibernateChangeUser(UserHibernate userHibernate) {

        User user = new User();
        user.setLogin(userHibernate.getLogin());
        user.setPassword(userHibernate.getPassword());
        user.setFirstName(userHibernate.getFirstName());
        user.setLastName(userHibernate.getLastName());
        user.setMiddleName(userHibernate.getMiddleName());
        user.setDateBirth(userHibernate.getDateBirth());


        return user;
    }

    public UserHibernate UserChangeUserHibernate(User user) {

        UserHibernate userHibernate = new UserHibernate();

        userHibernate.setLogin(user.getLogin());
        userHibernate.setPassword(user.getPassword());
        userHibernate.setFirstName(user.getFirstName());
        userHibernate.setLastName(user.getLastName());
        userHibernate.setMiddleName(user.getMiddleName());
        userHibernate.setDateBirth(user.getDateBirth());

        return userHibernate;
    }

    public static StorageUserHibernate getInstance() {
        return instance;
    }

}
