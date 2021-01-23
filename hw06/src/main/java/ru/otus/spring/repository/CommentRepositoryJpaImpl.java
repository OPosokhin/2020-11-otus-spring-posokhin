package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createQuery("select count(c) from Comment c", Long.class).getSingleResult();
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        return entityManager.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Comment commentToDelete = entityManager.find(Comment.class, id);
        entityManager.remove(commentToDelete);
    }
}
