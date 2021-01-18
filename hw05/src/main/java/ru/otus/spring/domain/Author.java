package ru.otus.spring.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class Author {
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    private final long id;
    private final String name;

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
