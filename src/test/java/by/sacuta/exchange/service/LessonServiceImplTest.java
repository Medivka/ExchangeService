package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.enums.LessonStatus;
import by.sacuta.exchange.domain.model.Lesson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonServiceImplTest {
//
//    @Resource
//    private LessonService lessonService;
//    @Resource
//    private CourseService courseService;
//
//
//    @Test
//    public void createNewLesson() {lessonService.createNewLesson("test", courseService.findById(1l),LocalDateTime.now(),90 , LessonStatus.GROUP, 4000);
//        Assert.assertNotNull(lessonService.findByName("test"));
//    }
//
//    @Test
//    @Transactional
//    public void update() {
//        Long id = 1l;
//        String name = "testName";
//        Lesson lesson = lessonService.findById(id);
//        lesson.setName(name);
//        lessonService.update(lesson);
//        Assert.assertEquals(name, lessonService.findById(id).getName());
//    }
//
//
//    @Test
//    public void findById() { Long id = 1l;
//        Lesson lesson = lessonService.findById(id);
//        Long id2 = lesson.getId();
//        Assert.assertEquals(id, id2);
//    }
//
//
//    @Test
//    public void getAll() {Assert.assertNotNull(lessonService.getAll());
//    }
//
//
//
//    @Test
//    public void findByStatus() {Assert.assertNotNull(lessonService.findByStatus(LessonStatus.GROUP));
//    }
//
//
//    @Test
//    public void save() {  Assert.assertNotNull(lessonService.findByName("test"));
//    }
//
//    @Test
//    public void findByDate() {Assert.assertNotNull(lessonService.findByDate(LocalDateTime.now()));
//    }
}