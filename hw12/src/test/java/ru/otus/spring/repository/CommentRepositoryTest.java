package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Comment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Comment repository test")
@DataJpaTest
class CommentRepositoryTest {
    static final long FIRST_COMMENT_ID = 1;
    @Autowired
    private CommentRepository сommentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("should count all entities")
    @Test
    void count() {
        long expectedQuantity = entityManager.getEntityManager().createQuery("select c from Comment c", Comment.class).
                getResultList().size();
        long actualQuantity = сommentRepository.count();
        assertEquals(expectedQuantity, actualQuantity);
    }


    @DisplayName("should find entity by id")
    @Test
    void shouldGetComment() {
        assertThat(сommentRepository.findById(1l).orElse(null))
                .isEqualTo(entityManager.find(Comment.class, 1l));
    }

    void findById() {
        Comment expectedComment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
        Comment actualComment = сommentRepository.findById(FIRST_COMMENT_ID).get();
        assertEquals(expectedComment, actualComment);
        assertEquals("Comment", actualComment.getText());
        assertEquals(1, actualComment.getBook().getId());
    }

    @DisplayName("should find all entities")
    @Test
    void findAll() {
        Set<Comment> expectedComments = new HashSet<>(entityManager.getEntityManager().createQuery("select c from Comment c", Comment.class).
                getResultList());
        List<Comment> actualCommentList = сommentRepository.findAll();
        Set<Comment> actualComments = new HashSet<>(actualCommentList);
        assertEquals(actualComments.size(), actualCommentList.size());
        assertEquals(expectedComments, actualComments);
    }

    @DisplayName("should delete entity")
    @Test
    void delete() {
        сommentRepository.deleteById(FIRST_COMMENT_ID);
        assertNull(entityManager.find(Comment.class, FIRST_COMMENT_ID));
    }
}