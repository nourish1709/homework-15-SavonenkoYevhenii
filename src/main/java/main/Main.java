package main;

import dao.AuthorDao;
import dao.BookDao;
import dao.UserDao;
import entities.Author;
import entities.Book;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final int LENGTH_OF_RANDOM_STRING = 10;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfiguration.class);

        UserDao user = applicationContext.getBean(UserDao.class);
        AuthorDao author = applicationContext.getBean(AuthorDao.class);
        BookDao book = applicationContext.getBean(BookDao.class);

        author.addAuthor(generateRandomString());
        System.out.println(author.getAuthorById(3));
        author.changeAuthorName(30, generateRandomString());
        author.deleteAuthor(10);

        book.addBook(generateRandomString());
        System.out.println(book.getBookById(13));
        book.changeBookTitle(13, generateRandomString());
        book.deleteBook(10);

        user.addUser(generateRandomString());
        System.out.println(user.getUserById(3));
        user.changeUserName(1, generateRandomString());
        user.deleteUser(10);

        for (Author a : author.getAllAuthors()) {
            System.out.println(a);
        }

        for (Book b : book.getAllBooks()) {
            System.out.println(b);
        }

        user.addBook(20, 30);

        System.out.println("\n\tIt's all users' books");

        for (String b : book.getAllUserBooks(3)) {
            System.out.println(b);
        }
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(LENGTH_OF_RANDOM_STRING);
    }
}
