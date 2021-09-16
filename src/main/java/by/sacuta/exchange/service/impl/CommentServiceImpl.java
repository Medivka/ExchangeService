package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.CommentDao;
import by.sacuta.exchange.domain.model.Comment;
import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
   // init method bean
    @PostConstruct
    public void init(){
        System.out.println("service comment bean create");
    }
    //destroy method
    @PreDestroy
    public void destroy(){
        System.out.println("service comment bean destroy");
    }
    @Override
    public void createNewComment(String message, Profile profile) {
        commentDao.save(Comment.builder()
                .message(message)
                .profile(profile)
                .build());
        LOGGER.info("create new comment");
    }

    @Override
    public List<Comment> getAll() {
        LOGGER.info("find all comment");
        return commentDao.findAll();
    }

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
        LOGGER.info("save comment: " + comment.getId());
    }

    @Override
    public Comment findById(Long id) {
        LOGGER.info("find by id comment: " + id);
        return commentDao.getById(id);

    }

    @Override
    public void update(Comment comment) {
        LOGGER.info("update comment: " + comment.getId());
        Comment commentInDB = commentDao.getById(comment.getId());
        commentInDB.setMessage(comment.getMessage());
        commentInDB.setProfile(comment.getProfile());
        commentDao.save(commentInDB);

    }

    @Override
    public void delete(Comment comment) {
        LOGGER.info("delete comment: " + comment.getId());
        commentDao.delete(comment);

    }
}
