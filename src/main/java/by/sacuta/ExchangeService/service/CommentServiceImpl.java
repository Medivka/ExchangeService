package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.dao.CommentDao;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.model.Comment;
import by.sacuta.ExchangeService.service.api.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void createNewComment(String massage, Profile profile) {
        commentDao.save(new Comment(massage, profile));
    }

    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.getById(id);
    }

    @Override
    public void update(Comment comment) {
        commentDao.save(comment);

    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }
}
