package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public long count() {

        return entityManager.createQuery("select count(a) from Author a", Long.class).getSingleResult();
    }


    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }


    @Override
    public List<Author> findByName(String name) {
        return entityManager.createQuery("select a from Author a where a.name = :name", Author.class)
                .setParameter("name", name).getResultList();
    }


    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.getId() == 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }
    @Override
    @Transactional
    public void delete(long id) {
        Author authorToRemove = entityManager.find(Author.class, id);
        entityManager.remove(authorToRemove);
    }

    }

