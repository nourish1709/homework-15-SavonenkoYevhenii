package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_to_author",
            joinColumns =
                    {@JoinColumn(name = "book_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "author_id")})
    private Set<Author> authorId;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthorId(Set<Author> authorId) {
        this.authorId = authorId;
    }

    public Set<Author> getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Book [ " +
                "title = \"" + title + '\"' +
                ", user = " + user +
                " ]";
    }
}
