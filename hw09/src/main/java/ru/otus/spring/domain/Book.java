package ru.otus.spring.domain;

import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER /*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @NonNull
    private Set<Author> author = new HashSet<>();

    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.EAGER /*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @NonNull
    private Set<Genre> genre = new HashSet<>();

    @NonNull
    @OneToMany(targetEntity = Comment.class, mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})

    private List<Comment> comment = new ArrayList<>();

    public Book(String name) {
        this.name = name;
    }

    public Book(long id) {
        this.id = id;
    }

    public void setName() {
    }

}
