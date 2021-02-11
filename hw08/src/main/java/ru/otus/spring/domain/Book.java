package ru.otus.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = "comments")
@NoArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
    private String id;

    private String name;

    @NonNull
    @DBRef
    private Set<Author> authors = new HashSet<>();

    @NonNull
    @DBRef
    private Set<Genre> genres = new HashSet<>();

    @NonNull
    private HashMap<UUID, Comment> comments = new HashMap<>();

}
