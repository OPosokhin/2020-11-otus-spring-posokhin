insert into genre (id, name)
values (1, 'Fantastic');
insert into genre (id, name)
values (2, 'Story');
insert into genre (id, name)
values (3, 'Detective');

insert into author (id, name)
values (1, 'J.K.Rowling');
insert into author (id, name)
values (2, 'Alan Alexander Milne');
insert into author (id, name)
values (3, 'Arthur Conan Doyle');

insert into book (id, name)
VALUES (1, 'Harry Potter and the Philosophers Stone');
insert into book (id, name)
VALUES (2, 'Wonnie-the-Pooh');
insert into book (id, name)
VALUES (3, 'Sherlock Holmes');

insert into book_author (book_id, author_id)
VALUES (1, 1);
insert into book_author (book_id, author_id)
VALUES (2, 2);
insert into book_author (book_id, author_id)
VALUES (3, 3);

insert into book_genre (book_id, genre_id)
VALUES (1, 1);
insert into book_genre (book_id, genre_id)
VALUES (2, 2);
insert into book_genre (book_id, genre_id)
VALUES (3, 3);

insert into comment (id, text, book_id)
VALUES (1, 'Comment', 1);
insert into comment (id, text, book_id)
VALUES (2, 'Comment2', 1);
insert into comment (id, text, book_id)
VALUES (3, 'Comment3', 1);

insert into comment (id, text, book_id)
VALUES (4, 'Comment4', 2);
insert into comment (id, text, book_id)
VALUES (5, 'Comment5', 2);
insert into comment (id, text, book_id)
VALUES (6, 'Comment6', 3);

INSERT INTO users (username, password, enabled)
values   ('admin', 'admin', 1),
         ('user', 'user', 1);


INSERT INTO authorities (username, authority)
values  ('admin', 'ROLE_ADMIN'),
        ('user', 'ROLE_USER');


