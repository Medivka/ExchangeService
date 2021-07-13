package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.Comment;
import by.sacuta.ExchangeService.model.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {

    void createNewComment(String massage, Profile profile);

    List<Comment> getAll();

    void save(Comment comment);

    Comment findById(Long id);

    void update(Comment comment);
    void delete(Comment comment);
}
