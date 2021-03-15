package ru.otus.spring.mongock.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog_old {

    private static final String GENRE = "genre";
    private static final String AUTHOR = "author";
    private static final String title = "title";
    private static final String AND = "$and";

    private static final String NAME = "name";

    @ChangeSet(order = "001", id = "addGenre", author = "oposokhin")
    public void insertGenre(DB db) {
        DBCollection myCollection = db.getCollection("genre");
        myCollection.insert(
                new BasicDBObject().append(NAME, "Fantastic"),
                new BasicDBObject().append(NAME, "story"),
                new BasicDBObject().append(NAME, "Detective"));
    }

    @ChangeSet(order = "002", id = "addAuthor", author = "oposokhin")
    public void insertAuthor(DB db) {
        DBCollection myCollection = db.getCollection("author");
        myCollection.insert(
                new BasicDBObject().append(NAME, "J.K.Rowling"),
                new BasicDBObject().append(NAME, "Alan Alexander Milne"),
                new BasicDBObject().append(NAME, "Arthur Conan Doyle"));
    }

    @ChangeSet(order = "003", id = "addBook", author = "oposokhin")
    public void insertBook(DB db) {
        DBRef author = getAuthor(db);
        DBRef genre = getGenre(db);

        DBCollection myCollection = db.getCollection("book");
        myCollection.insert(
                new BasicDBObject().append(title, "Harry Potter and the Philosophers Stone").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(title, "Wonnie-the-Pooh").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(title, "Sherlock Holmes").append(AUTHOR, author).append(GENRE, genre)
        );
    }

    private DBRef getGenre(DB db) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", "Fantastic");
        DBObject genre = db.getCollection("genre").findOne(query);
        return new DBRef("genre", genre.get("_id"));
    }

    private DBRef getAuthor(DB db) {
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject(NAME, "J.K.Rowling"));;
        andQuery.put(AND, obj);
        DBObject author = db.getCollection("author").findOne(andQuery);
        return new DBRef("author", author.get("_id"));
    }
    

    }

