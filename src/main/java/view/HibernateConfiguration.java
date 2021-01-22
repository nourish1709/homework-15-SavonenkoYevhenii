package view;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dao")
public class HibernateConfiguration {

    @Bean
    SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Something goes wrong " + e);
        }
        return sessionFactory;
    }
}
