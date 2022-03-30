package by.it_academy.jd2.m_jd2_88_22.chat.endpoints.listener;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.FactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.HibernateDBInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ChatListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

      //  sce.getServletContext().setAttribute("storage","hibernate");
        FactoryStorage.getInstance().setChoice("memory");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        HibernateDBInitializer.getInstance().getManager().getEntityManagerFactory().close();

    }
}
