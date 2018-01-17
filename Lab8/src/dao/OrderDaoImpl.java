package dao;

import model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private BaseDao baseDao;

    private static int PAGE_SIZE = 2;

    public int getListOrderPageCountByUsername(String username) {
        int count = -1;
        try {
            Session session = baseDao.getNewSession();

            String hql = "SELECT COUNT(o) FROM Order o WHERE username = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1, username);

            count = (int) ((long) query.getResultList().get(0));

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count / PAGE_SIZE + (count % PAGE_SIZE == 0 ? 0 : 1);
    }

    public ArrayList<Order> getListOrderByUsernameAndPage(String username, int page) {
        ArrayList<Order> resultOrder = new ArrayList<>();
        try {
            Session session = baseDao.getNewSession();

            String hql = "SELECT o FROM Order o WHERE username = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1, username);
            query.setFirstResult((page - 1) * PAGE_SIZE);
            query.setMaxResults(PAGE_SIZE);

            for (Object object : query.getResultList()) {
                resultOrder.add((Order) object);
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOrder;
    }

}
