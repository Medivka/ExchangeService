package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Long> {
}
