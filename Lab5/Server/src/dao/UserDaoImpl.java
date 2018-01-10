package dao;

import model.User;

import javax.persistence.*;

public class UserDaoImpl implements UserDao {

    @PersistenceUnit(name = "J2EELab5JPA")
    private EntityManagerFactory factory;

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl() {
        factory = Persistence.createEntityManagerFactory("J2EELab5JPA");
        em = factory.createEntityManager();
    }

    public User findUser(String username, String password) {
        User user = null;
        try {
            user = em.find(User.class, username);
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
