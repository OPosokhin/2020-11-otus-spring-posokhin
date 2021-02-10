CREATE TABLE if not exists author
(
    id   BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(255)
);

CREATE TABLE if not exists book
(
    id   BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(255)
);
CREATE TABLE if not exists genre
(
    id   BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(255)
);

CREATE TABLE if not exists comment
(
    id        BIGINT PRIMARY KEY auto_increment,
    text      VARCHAR(255),
    book_id   BIGINT references book (id) on delete cascade
);


CREATE TABLE if not exists book_author
(
    book_id   BIGINT,
    author_id BIGINT,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) references book (id) on delete cascade,
    FOREIGN KEY (author_id) references author (id) on delete cascade
);

CREATE TABLE if not exists book_genre
(
    book_id  BIGINT references book (id) on delete cascade,
    genre_id BIGINT references genre (id) on delete cascade,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) references book (id) on delete cascade,
    FOREIGN KEY (genre_id) references genre (id) on delete cascade
);
