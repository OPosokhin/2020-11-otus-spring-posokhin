package ru.otus.spring.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "uuid")
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {
    private UUID uuid;
    @NonNull
    private String text;

    @NonNull
    private Instant timestamp;

    public Comment(String text, Instant timestamp) {
        this.uuid = UUID.randomUUID();
        this.text = text;
        this.timestamp = timestamp;
    }
}
