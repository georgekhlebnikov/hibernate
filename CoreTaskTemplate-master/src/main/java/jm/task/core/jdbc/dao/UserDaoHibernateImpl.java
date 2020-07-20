package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public final SessionFactory sessionFactory = Util.getSessionfactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT auto_increment, " +
                " name VARCHAR(64), " +
                " lastname VARCHAR(64), " +
                " age TINYINT, " +
                " PRIMARY KEY (id))";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Created table 'users' in database..");
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица 'users' \u2014 удалена");
        session.close();
        sessionFactory.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = this.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        User u1 = new User();
        u1.setName(name);
        u1.setLastName(lastName);
        u1.setAge(age);
        session.persist(u1);
        System.out.println("User '" + name + " " + lastName + "' \u2014 добавлен");
        t.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User u1 = (User) session.get(User.class, id);
        session.delete(u1);
        System.out.println("User с ID=" + id + " \u2014 удален");
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM users").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = this.sessionFactory.openSession();
        String users = "users";
        Transaction t = session.beginTransaction();
        String hql = String.format("DELETE FROM %s", users);
        Query query = session.createQuery(hql);
        query.executeUpdate();
        System.out.println("Таблица 'users' \u2014 очищена");
        t.commit();
        session.close();
    }
}
