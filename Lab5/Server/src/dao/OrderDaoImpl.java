package dao;

import model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

@Stateless
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    private static final int PAGE_SIZE = 2;

    public int getListOrderPageCountByUsername(String username) {
        int count = -1;
        try {
            Query query = em.createQuery("SELECT COUNT(o) FROM Order o WHERE o.username = ?1");
            query.setParameter(1, username);
            count = (int) ((long) query.getResultList().get(0));
            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
    }

    public ArrayList<Order> getListOrderByUsernameAndPage(String username, int page) {
        ArrayList<Order> resultOrder = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT o FROM Order o WHERE o.username = ?1");
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
