package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
}
