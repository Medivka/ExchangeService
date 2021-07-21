package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.domain.model.Comment;

import java.util.List;


public interface CommentService {

    void createNewComment(String massage, Profile profile);
    List<Comment> getAll();
    void save(Comment comment);
    Comment findById(Long id);
    void update(Comment comment);
    void delete(Comment comment);
}
