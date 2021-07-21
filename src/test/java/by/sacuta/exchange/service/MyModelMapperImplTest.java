package by.sacuta.exchange.service;

import by.sacuta.exchange.dto.CommentDTO;
import by.sacuta.exchange.dto.CourseDTO;
import by.sacuta.exchange.dto.LessonDTO;
import by.sacuta.exchange.dto.ProfileDTO;
import by.sacuta.exchange.domain.model.Comment;
import by.sacuta.exchange.domain.model.Profile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyModelMapperImplTest {

    @Resource
    private ProfileService profileService;


    @Resource
    private CommentService commentService;
    @Resource
    private LessonService lessonService;
    @Resource
    private CourseService courseService;
    @Resource
    private MyModelMapper myModelMapper;

    @Test
    @Transactional
    public void mapToCommentDTO() {
        CommentDTO commentDTO=myModelMapper.mapToCommentDTO(commentService.findById(1l));
        Assert.assertEquals(commentDTO.getMessage(),(commentService.findById(1l).getMessage()));
    }

    @Test
    @Transactional
    public void mapToComment() {
        Comment comment=commentService.findById(1l);
        CommentDTO commentDTO= myModelMapper.mapToCommentDTO(comment);
        Assert.assertEquals(commentDTO.getMessage(),(commentService.findById(1l).getMessage()));
    }

    @Test
    @Transactional
    public void mapToCourseDTO() {
        CourseDTO courseDTO= myModelMapper.mapToCourseDTO(courseService.findById(1l));
        Assert.assertEquals(courseDTO.getName(),(courseService.findById(1l).getName()));
    }

    @Test
    @Transactional
    public void mapToCourse() {
        CourseDTO courseDTO= myModelMapper.mapToCourseDTO(courseService.findById(1l));
        Assert.assertEquals(courseDTO.getName(),(courseService.findById(1l).getName()));
    }

    @Test
    @Transactional
    public void mapToLessonDTO() {
        LessonDTO lessonDTO= myModelMapper.mapToLessonDTO(lessonService.findById(1l));
        Assert.assertEquals(lessonDTO.getName(),(lessonService.findById(1l).getName()));
    }

    @Test
    @Transactional
    public void mapToLesson() {
        LessonDTO lessonDTO= myModelMapper.mapToLessonDTO(lessonService.findById(1l));
        Assert.assertEquals(lessonDTO.getName(),(lessonService.findById(1l).getName()));
    }

    @Test
    @Transactional
    public void mapTOClient() {Long id = 1l;
        Profile profile = profileService.findByID(id);
        ProfileDTO profileDTO = myModelMapper.mapToProfileDTO(profile);
        Assert.assertEquals(profile.getName(), profileDTO.getName());
        Assert.assertEquals(profile.getUsername(), profileDTO.getUsername());
        Assert.assertEquals(profile.getAge(), profileDTO.getAge());
        Assert.assertEquals(profile.getCity(), profileDTO.getCity());
        Assert.assertEquals(profile.getEmail(), profileDTO.getEmail());
    }
    @Test
    @Transactional
    public void mapToClientDTO() {Long id = 1l;
        Profile profile = profileService.findByID(id);
        ProfileDTO profileDTO = myModelMapper.mapToProfileDTO(profile);
        Assert.assertEquals(profile.getName(), profileDTO.getName());
        Assert.assertEquals(profile.getUsername(), profileDTO.getUsername());
        Assert.assertEquals(profile.getAge(), profileDTO.getAge());
        Assert.assertEquals(profile.getCity(), profileDTO.getCity());
        Assert.assertEquals(profile.getEmail(), profileDTO.getEmail());
    }
}