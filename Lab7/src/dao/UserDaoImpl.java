package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDao = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return userDao;
    }

    public User findUser(String username, String password) {
        User user = null;
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new  StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();
            user = session.find(User.class, username);
            transaction.commit();

            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
