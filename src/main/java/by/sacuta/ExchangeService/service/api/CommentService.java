package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.model.Comment;

import java.util.List;


public interface CommentService {

    void createNewComment(String massage, Role.Profile profile);

    List<Comment> getAll();

    void save(Comment comment);

    Comment findById(Long id);

    void update(Comment comment);
    void delete(Comment comment);
}
