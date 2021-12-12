package utils;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(MovieEntity.class);
            configuration.addAnnotatedClass(TagEntity.class);
            configuration.addAnnotatedClass(LinkEntity.class);
            configuration.addAnnotatedClass(GenreEntity.class);
            configuration.addAnnotatedClass(RatingEntity.class);
            configuration.addAnnotatedClass(UserEntity.class);
            SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
            return factory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
