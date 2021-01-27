package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpaImpl implements GenreRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(g) from Genre g", Long.class).getSingleResult();
    }

    @Override
    public List<Genre> findByName(String name) {
        return entityManager.createQuery("select g from Genre g where g.name = :name", Genre.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        Genre genreToDelete = entityManager.find(Genre.class, id);
        entityManager.remove(genreToDelete);
    }
}
