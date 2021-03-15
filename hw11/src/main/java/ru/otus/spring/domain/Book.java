package ru.otus.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
    private String id;

    private String name;

    @NonNull
    @DBRef
    private Set<Author> author = new HashSet<>();

    @NonNull
    @DBRef
    private Set<Genre> genre = new HashSet<>();


    public Book(String name) {
        this.name = name;
    }
}
