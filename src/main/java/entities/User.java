package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Book> bookList;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public Set<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book) {
        if (!bookList.contains(book))
            bookList.add(book);
    }

    @Override
    public String toString() {
        return "User_test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
