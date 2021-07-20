package by.sacuta.exchangeService.service.api;

import by.sacuta.exchangeService.model.model.Profile;
import by.sacuta.exchangeService.model.model.Comment;

import java.util.List;


public interface CommentService {

    void createNewComment(String massage, Profile profile);
    List<Comment> getAll();
    void save(Comment comment);
    Comment findById(Long id);
    void update(Comment comment);
    void delete(Comment comment);
}
