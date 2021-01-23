package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpaImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("select count(b) from Book b", Long.class).getSingleResult();
    }

    @Override
    public Optional<Book> findById(long id) {

        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findByName(String name) {
        return entityManager.createQuery("select b from Book b where b.name = :name", Book.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() == 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }
    @Override
    @Transactional
    public void delete(long id) {
        Book bookToDelete = entityManager.find(Book.class, id);
        entityManager.remove(bookToDelete);
    }
    }

