package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.model.enums.CourseStatus;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.service.api.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {
    @Resource
    private ProfileService profileService;

    @Resource
    private CommentService commentService;
    @Resource
    private LessonService lessonService;
    @Resource
    private CourseService courseService;
    @Resource
    private SectionService sectionService;

    @Test
    @Transactional
    public void createNewCourse() {
        String name = "courseTest";
        courseService.createNewCourse(name, sectionService.getById(1l) ,LocalDate.now(), 90, profileService.findByID(1l), CourseStatus.OPEN, lessonService.findById(1l), profileService.findByID(2l), commentService.findById(1l));
        Assert.assertNotNull(courseService.findByName(name));
    }

    @Test
    @Transactional
    public void update() { Long id = 1l;
        String name = "testName";
        Course course = courseService.findById(id);
        course.setName(name);
        courseService.update(course);
        Assert.assertEquals(name, courseService.findById(id).getName());
    }

    @Test
    public void findById() {  Long id = 1l;
        Course course = courseService.findById(id);
        Long id2 = course.getId();
        Assert.assertEquals(id, id2);
    }

    @Test
    public void getAll() {Assert.assertNotNull(courseService.getAll());
    }

    @Test
    public void getCourseByStatus() {Assert.assertNotNull(courseService.getCourseByStatus(CourseStatus.OPEN));
    }

    @Test
    public void getCourseAfterThisDate() {
        Assert.assertNotNull(courseService.getCourseAfterThisDate(LocalDate.now()));
    }

    @Test
    @Transactional
    public void changeSpeaker() { Long id = 1l;
        Profile speaker = profileService.getAllSpeaker().get(0);
        Course course = courseService.findById(id);
        courseService.changeSpeaker(course, speaker);
        Assert.assertEquals(speaker, course.getSpeaker());
    }

    @Test
    public void findByName() {
        String name = "courseTest";
        Assert.assertNotNull(courseService.findByName(name));
    }
}