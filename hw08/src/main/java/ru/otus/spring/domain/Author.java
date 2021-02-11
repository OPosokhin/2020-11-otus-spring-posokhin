package ru.otus.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Document(collection = "author")
public class Author {
    @Id
    private String id;
    @NonNull
    private String name;

    public Author(String name) {

        this.name = name;
    }

}

