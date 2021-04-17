insert into genre (genre_id, name)
values (1, 'Fantastic');
insert into genre (genre_id, name)
values (2, 'Story');
insert into genre (genre_id, name)
values (3, 'Detective');

insert into author (author_id, name)
values (1, 'J.K.Rowling');
insert into author (author_id, name)
values (2, 'Alan Alexander Milne');
insert into author (author_id, name)
values (3, 'Arthur Conan Doyle');

insert into book (book_id, name, genre_id, author_id)
VALUES (1, 'Harry Potter and the Philosophers Stone', 1, 1);
insert into book (book_id, name, genre_id, author_id)
VALUES (2, 'Wonnie-the-Pooh',2, 2);
insert into book (book_id, name, genre_id, author_id)
VALUES (3, 'Sherlock Holmes',3 ,3);


insert into comment (comment_id, comment, book_id)
VALUES (1, 'Comment', 1);
insert into comment (comment_id, comment, book_id)
VALUES (2, 'Comment2', 1);
insert into comment (comment_id, comment, book_id)
VALUES (3, 'Comment3', 1);

insert into comment (comment_id, comment, book_id)
VALUES (4, 'Comment4', 2);
insert into comment (comment_id, comment, book_id)
VALUES (5, 'Comment5', 2);
insert into comment (comment_id, comment, book_id)
VALUES (6, 'Comment6', 3);


