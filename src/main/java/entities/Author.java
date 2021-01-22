package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authorId")
    private Set<Book> bookId;

    public Author() {
    }

    public Author(String name) {
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

    public void setBookId(Set<Book> bookId) {
        this.bookId = bookId;
    }

    public Set<Book> getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Author [ " +
                "name = " + name +
                " ]";
    }
}
