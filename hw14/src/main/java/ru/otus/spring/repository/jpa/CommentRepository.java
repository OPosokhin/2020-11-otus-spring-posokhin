package ru.otus.spring.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.jpa.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>  {

}

