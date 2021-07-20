package by.sacuta.exchangeService.dao;

import by.sacuta.exchangeService.model.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
}
