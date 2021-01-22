package dao;

import entities.Book;
import entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private SessionFactory factory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    public void addUser(String  name) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name);
            session.save(user);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public User getUserById(int id) {
        User user;
        try (Session session = factory.openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = null;

        try (Session session = factory.openSession()){
            userList = session.createQuery("FROM User").list();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return userList;
    }

    public void changeUserName(int id, String newName) {
        Transaction transaction = null;

        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            User user = getUserById(id);
            user.setName(newName);
            session.update(user);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            User user = getUserById(id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public void addBook(int userId, int bookId) {
        BookDao bookDao = new BookDao();
        User user = getUserById(userId);
        Book book = bookDao.getBookById(bookId);
        if (!user.getBookList().contains(book)) {
            user.addBook(book);
            new BookDao().setUser(bookId, user);
        }
    }
}
