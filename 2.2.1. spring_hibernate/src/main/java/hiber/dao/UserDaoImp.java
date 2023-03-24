package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.openSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(String carModel, int carSeries) {
        TypedQuery<User> query = sessionFactory.openSession().createQuery("FROM User c WHERE c.car.model= :model AND c.car.series= :series", User.class);
        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);
        return query.getSingleResult();
    }
}
