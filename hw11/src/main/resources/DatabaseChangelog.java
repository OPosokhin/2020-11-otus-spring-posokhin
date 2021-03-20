package ru.otus.spring.mongock.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Collections;

@ChangeLog
@Slf4j
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "initialData", author = "oposokhin")
    public void t(MongoDatabase db) {
        MongoCollection<Document> author = db.getCollection("author");
        Document author1 = new Document("name", "J.K.Rowling");
        String authorId1 = author.insertOne(author1).getInsertedId().toString();

        Document author2 = new Document("name", "Alan Alexander Milne");
        String authorId2 = author.insertOne(author2).getInsertedId().toString();

        Document author3 = new Document("name", "Arthur Conan Doyle");
        String authorId3 = author.insertOne(author3).getInsertedId().toString();

        MongoCollection<Document> genre = db.getCollection("genre");

        Document genre1 = new Document("name", "Fantastic");
        String genreId1 = genre.insertOne(genre1).getInsertedId().toString();

        Document genre2 = new Document("name", "Story");
        String genreId2 = genre.insertOne(genre2).getInsertedId().toString();

        Document genre3= new Document("name", "Detective");
        String genreId3 = genre.insertOne(genre3).getInsertedId().toString();

        MongoCollection<Document> book = db.getCollection("book");

        Document book1 = new Document("name", "Harry Potter and the Philosophers Stone")
                .append("author", (new DBRef("author", authorId1)))
                .append("genre", (new DBRef("genre", genreId1)));
        book.insertOne(book1);

        Document book2 = new Document("name", "Wonnie-the-Pooh")
                .append("author", Collections.singletonList(new DBRef("author", authorId2.get("_id"))))
                .append("genre", Collections.singletonList(new DBRef("genre", genreId2)));

        book.insertOne(book2);

        Document book3 = new Document("name", "Sherlock Holmes")
                .append("author", Collections.singletonList(new DBRef("author", authorId3)))
                .append("genre", Collections.singletonList(new DBRef("genre", genreId3)));

        book.insertOne(book3);
    }
}
