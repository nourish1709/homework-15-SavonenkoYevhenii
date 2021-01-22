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
public class BookDao {

    private static SessionFactory factory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    public void addBook(String  title) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Book bookTest = new Book(title);
            session.save(bookTest);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public Book getBookById(int id) {
        Book book;
        try (Session session = factory.openSession()) {
            book = session.get(Book.class, id);
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = null;
        try (Session session = factory.openSession()){
            bookList = session.createQuery("FROM Book").list();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return bookList;
    }

    public void changeBookTitle(int id, String newTitle) {
        Transaction transaction = null;
        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Book book = getBookById(id);
            book.setTitle(newTitle);
            session.update(book);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Book book = getBookById(id);
            session.delete(book);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public void setUser(int id, User user) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Book bookTest = getBookById(id);
            bookTest.setUser(user);
            session.update(bookTest);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public List<String> getAllUserBooks(int userId) {
        List<String> bookList = null;
        try (Session session = factory.openSession()){
            bookList = session.createQuery("SELECT title FROM Book WHERE user = " + userId).list();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return bookList;
    }
}