package dao;

import model.Order;

import javax.persistence.*;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    @PersistenceUnit(name = "J2EELab5JPA")
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager em;

    private static final int PAGE_SIZE = 2;

    public OrderDaoImpl() {
        factory = Persistence.createEntityManagerFactory("J2EELab5JPA");
        em = factory.createEntityManager();
    }

    public int getListOrderPageCountByUsername(String username) {
        int count = -1;
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Order o WHERE o.username = ?1", Long.class);
            query.setParameter(1, username);
            count = (int) (long) query.getResultList().get(0);
            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
    }

    public ArrayList<Order> getListOrderByUsernameAndPage(String username, int page) {
        ArrayList<Order> resultOrder = new ArrayList<>();
        try {
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.username = ?1", Order.class);
            query.setParameter(1, username);
            query.setFirstResult((page - 1) * PAGE_SIZE);
            query.setMaxResults(PAGE_SIZE);

            for (Object object : query.getResultList()) {
                resultOrder.add((Order) object);
            }
            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOrder;
    }

}
