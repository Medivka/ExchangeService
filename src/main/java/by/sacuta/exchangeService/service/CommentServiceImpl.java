package by.sacuta.exchangeService.service;

import by.sacuta.exchangeService.dao.CommentDao;
import by.sacuta.exchangeService.exception.MyServiceException;
import by.sacuta.exchangeService.model.model.Profile;
import by.sacuta.exchangeService.model.model.Comment;
import by.sacuta.exchangeService.service.api.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void createNewComment(String massage, Profile profile) {
        try {
            commentDao.save(new Comment(massage, profile));
            LOGGER.info("create new comment");
        } catch (MyServiceException e) {
            LOGGER.warn("create new Comment failed ", e);
            throw new MyServiceException("create new Comment failed ", e);
        }
    }

    @Override
    public List<Comment> getAll() {
        try {
            LOGGER.info("find all comment");
            return commentDao.findAll();
        } catch (MyServiceException e) {
            LOGGER.warn("find all comment failed ", e);
            throw new MyServiceException("find all comment failed ", e);
        }
    }

    @Override
    public void save(Comment comment) {
        try {
            commentDao.save(comment);
            LOGGER.info("save comment: " + comment.getId());
        } catch (MyServiceException e) {
            LOGGER.warn("save comment failed " + comment.getId(), e);
            throw new MyServiceException("save comment failed " + comment.getId(), e);
        }

    }

    @Override
    public Comment findById(Long id) {
        try {
            LOGGER.info("find by id comment: " + id);
            return commentDao.getById(id);
        } catch (MyServiceException e) {
            LOGGER.warn("find by id comment failed " + id, e);
            throw new MyServiceException("find by id comment failed " + id, e);
        }
    }

    @Override
    public void update(Comment comment) {
        try {
            LOGGER.info("update comment: " + comment.getId());
            commentDao.save(comment);
        } catch (MyServiceException e) {
            LOGGER.warn("update comment failed " + comment.getId(), e);
            throw new MyServiceException("update comment failed " + comment.getId(), e);
        }
    }

    @Override
    public void delete(Comment comment) {
        try {
            LOGGER.info("delete comment: " + comment.getId());
            commentDao.delete(comment);
        } catch (MyServiceException e) {
            LOGGER.warn("delete comment failed " + comment.getId(), e);
            throw new MyServiceException("delete comment failed " + comment.getId(), e);
        }
    }
}
