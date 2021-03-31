DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS comment;



CREATE TABLE genre (
                        genre_id IDENTITY NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        CONSTRAINT genre_id PRIMARY KEY (genre_id)
);


CREATE TABLE author (
                         author_id IDENTITY NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         CONSTRAINT author_id PRIMARY KEY (author_id)
);


CREATE TABLE book (
                       book_id IDENTITY NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       genre_id BIGINT NOT NULL,
                       author_id BIGINT NOT NULL,
                       CONSTRAINT book_id PRIMARY KEY (book_id)
);


CREATE TABLE comment (
                          comment_id IDENTITY NOT NULL,
                          comment VARCHAR(1000) NOT NULL,
                          book_id BIGINT NOT NULL,
                          CONSTRAINT comment_id PRIMARY KEY (comment_id)
);



ALTER TABLE book ADD CONSTRAINT genres_books_fk
    FOREIGN KEY (genre_id)
        REFERENCES genre (genre_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION;

ALTER TABLE book ADD CONSTRAINT authors_books_fk
    FOREIGN KEY (author_id)
        REFERENCES author (author_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION;

ALTER TABLE comment ADD CONSTRAINT books_comments_fk
    FOREIGN KEY (book_id)
        REFERENCES book (book_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION;