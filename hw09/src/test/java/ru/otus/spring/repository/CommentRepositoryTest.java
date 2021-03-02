package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Comment JPA repository test")
@DataJpaTest
class CommentRepositoryTest {
    static final long FIRST_COMMENT_ID = 1;
    @Autowired
    private CommentRepository repositoryJpa;
    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("should count all entities")
    @Test
    void count() {
        long expectedQuantity = entityManager.getEntityManager().createQuery("select c from Comment c", Comment.class).
                getResultList().size();
        long actualQuantity = repositoryJpa.count();
        assertEquals(expectedQuantity, actualQuantity);
    }

    @DisplayName("should generate id on save")
    @Test
    void saveIdTest() {
        Comment newComment = new Comment(0, "text1",  new Book(1));
        repositoryJpa.save(newComment);
        assertNotEquals(0, newComment.getId());
    }

    @DisplayName("should persist entity")
    @Test
    void save() {
        Comment newComment = new Comment(0, "text2", new Book(1));
        repositoryJpa.save(newComment);
        Comment foundComment = entityManager.find(Comment.class, newComment.getId());
        assertEquals(newComment, foundComment);
    }

    @DisplayName("should find entity by id")
    @Test
    void findById() {
        Comment expectedComment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
        Comment actualComment = repositoryJpa.findById(FIRST_COMMENT_ID).get();
        assertEquals(expectedComment, actualComment);
        assertEquals("Comment", actualComment.getText());
        assertEquals(1, actualComment.getBook().getId());
    }

    @DisplayName("should find all entities")
    @Test
    void findAll() {
        Set<Comment> expectedComments = new HashSet<>(entityManager.getEntityManager().createQuery("select c from Comment c", Comment.class).
                getResultList());
        List<Comment> actualCommentList = repositoryJpa.findAll();
        Set<Comment> actualComments = new HashSet<>(actualCommentList);
        assertEquals(actualComments.size(), actualCommentList.size());
        assertEquals(expectedComments, actualComments);
    }

    @DisplayName("should delete entity")
    @Test
    void delete() {
        repositoryJpa.deleteById(FIRST_COMMENT_ID);
        assertNull(entityManager.find(Comment.class, FIRST_COMMENT_ID));
    }
}