package dao;

import entities.Author;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDao {

    private SessionFactory factory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    public void addAuthor(String  name) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Author author = new Author(name);
            session.save(author);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public Author getAuthorById(int id) {
        Author author;
        try (Session session = factory.openSession()) {
            author = session.get(Author.class, id);
        }
        return author;
    }

    public List<Author>  getAllAuthors() {
        List<Author> authorList = null;

        try (Session session = factory.openSession()){
            authorList = session.createQuery("FROM Author").list();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return authorList;
    }

    public void changeAuthorName(int id, String newName) {
        Transaction transaction = null;

        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Author author = getAuthorById(id);
            author.setName(newName);
            session.update(author);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }

    public void deleteAuthor(int id) {
        Transaction transaction = null;
        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Author author = getAuthorById(id);
            session.delete(author);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null)
                transaction.rollback();
            exception.printStackTrace();
        }
    }
}
