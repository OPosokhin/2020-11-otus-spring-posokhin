package ru.otus.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private String id;
    
    @Field("lastname")
    private String lastname;
    
    @Field("firstname")
    private String firstname;
    
    @Field("middlename")
    private String middlename;
    
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @DBRef
    private Set<Book> books;
}
