package view;

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
        System.out.println("\n\tauthor with id 1:\t" + author.getAuthorById(1));
        author.changeAuthorName(1, generateRandomString());
        System.out.println("\tauthor with id 1 after changing his name:\t" + author.getAuthorById(1));
        author.deleteAuthor(70);

        book.addBook(generateRandomString());
        System.out.println("\n\tbook with id 30:\t" + book.getBookById(30));
        book.changeBookTitle(30, generateRandomString());
        System.out.println("\tbook with id 30 after changing its title:\t" + book.getBookById(30));
        book.deleteBook(10);

        user.addUser(generateRandomString());
        System.out.println("\n\tuser with id 10:\t" + user.getUserById(10));
        user.changeUserName(10, generateRandomString());
        System.out.println("\tuser with id 10 after changing his name:\t" + user.getUserById(10));
        user.deleteUser(10);

        System.out.println("\nall authors:");
        for (Author a : author.getAllAuthors()) {
            System.out.println("\t" + a);
        }

        System.out.println("\nall books:");
        for (Book b : book.getAllBooks()) {
            System.out.println("\t" + b);
        }

        user.addBook(10, 32);

        System.out.println("\nall books of user with id 3:");
        for (String b : book.getAllUserBooks(3)) {
            System.out.println("\t" + b);
        }
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(LENGTH_OF_RANDOM_STRING);
    }
}
