drop table if exists book CASCADE;
CREATE TABLE if not exists book
(
    id   serial PRIMARY KEY,
    name VARCHAR(255)
);
drop table if exists author CASCADE;
CREATE TABLE if not exists author
(
    id   serial PRIMARY KEY,
    name VARCHAR(255)
);

drop table if exists genre CASCADE;
CREATE TABLE if not exists genre
(
    id   serial PRIMARY KEY,
    name VARCHAR(255)
);
drop table if exists comment CASCADE;
CREATE TABLE if not exists comment
(
    id        serial PRIMARY KEY,
    text      VARCHAR(255),
    book_id   BIGINT references book (id) on delete cascade
);


drop table if exists book_author CASCADE;
CREATE TABLE if not exists book_author
(
    book_id   bigint,
    author_id bigint,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) references book (id) on delete cascade,
    FOREIGN KEY (author_id) references author (id) on delete cascade
);
drop table if exists book_genre CASCADE;
CREATE TABLE if not exists book_genre
(
    book_id  bigint not null references book (id) on delete cascade,
    genre_id bigint not null references genre (id) on delete cascade,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) references book (id) on delete cascade,
    FOREIGN KEY (genre_id) references genre (id) on delete cascade
);
drop table if exists users CASCADE;
CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  INT  NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);
drop table if exists authorities CASCADE;
CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

drop INDEX if exists ix_auth_username;
CREATE UNIQUE INDEX ix_auth_username
    on authorities (username, authority);

