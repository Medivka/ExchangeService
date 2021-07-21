package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {
    @Resource
    CommentService commentService;
    @Resource
    ProfileService profileService;

    @Test
    public void createNewComment() {
        commentService.createNewComment("it's test", profileService.findByUsername("admin"));
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(commentService.getAll());
    }

    @Test
    public void findById() {
        Long id = 1l;
        Comment comment = commentService.findById(1l);
        Long id2 = comment.getId();
        Assert.assertEquals(id, id2);
    }

    @Test
    @Transactional
    public void update() {
        Long id = 1l;
        String massage = "update";
        Comment comment = commentService.findById(id);
        comment.setMessage(massage);
        commentService.update(comment);
        Assert.assertEquals(massage, commentService.findById(id).getMessage());
    }
}