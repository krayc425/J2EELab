package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private BaseDao baseDao;

    public User findUser(String username, String password) {
        User user = null;
        try {
            Session session = baseDao.getNewSession();

            Transaction transaction = session.beginTransaction();
            user = session.find(User.class, username);
            transaction.commit();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
