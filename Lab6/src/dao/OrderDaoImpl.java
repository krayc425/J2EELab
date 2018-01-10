package dao;

import model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    private static OrderDaoImpl orderDao = new OrderDaoImpl();

    public static OrderDaoImpl getInstance() {
        return orderDao;
    }

    private static int PAGE_SIZE = 2;

    public int getListOrderPageCountByUsername(String username) {
        int count = -1;
        try {
            Configuration config = new Configuration().configure();
            config.addAnnotatedClass(Order.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();

            String hql = "SELECT COUNT(o) FROM Order o WHERE username = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1, username);

            count = (int) ((long) query.getResultList().get(0));

            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
    }

    public ArrayList<Order> getListOrderByUsernameAndPage(String username, int page) {
        ArrayList<Order> resultOrder = new ArrayList<>();
        try {

            Configuration config = new Configuration().configure();
            config.addAnnotatedClass(Order.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();

            String hql = "SELECT o FROM Order o WHERE username = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1, username);
            query.setFirstResult((page - 1) * PAGE_SIZE);
            query.setMaxResults(PAGE_SIZE);

            for (Object object : query.getResultList()) {
                resultOrder.add((Order) object);
            }

            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOrder;
    }

}
