package ru.otus.spring.domain;

import lombok.*;
import javax.persistence.*;

@Data
@ToString(exclude = {"book"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(name = "text", nullable = false)
    private String text;

    @NonNull
    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

}
