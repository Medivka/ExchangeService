package by.sacuta.exchange.dao;

import by.sacuta.exchange.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
}
