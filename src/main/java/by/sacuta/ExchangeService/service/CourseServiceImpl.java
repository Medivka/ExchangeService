package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.dao.CommentDao;
import by.sacuta.ExchangeService.dao.CourseDao;
import by.sacuta.ExchangeService.dao.LessonDao;
import by.sacuta.ExchangeService.dao.ProfileDao;
import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.model.Comment;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.model.enums.CourseStatus;
import by.sacuta.ExchangeService.model.enums.ProfileStatus;
import by.sacuta.ExchangeService.service.api.CourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;
    private final CommentDao commentDao;
    private final LessonDao lessonDao;
    private final ProfileDao profileDao;

    public CourseServiceImpl(CourseDao courseDao, CommentDao commentDao, LessonDao lessonDao, ProfileDao profileDao) {
        this.courseDao = courseDao;
        this.commentDao = commentDao;
        this.lessonDao = lessonDao;
        this.profileDao = profileDao;
    }

    @Override
    public void createNewCourse(String name, LocalDate startDate, Integer days, Role.Profile speaker, CourseStatus courseStatus, Lesson lesson, Role.Profile clients, Comment comment) {
        Integer price = 0;
        Course course = new Course();
        course.setName(name);
        course.setStartCourse(startDate);
        course.setDays(days);
        course.setEndCourse(startDate.plusDays(days));
        List<Lesson> lessons = new LinkedList<>();
        lessons.add(lesson);
        course.setLessons(lessons);
        List<Role.Profile> listenerList = new LinkedList<>();
        listenerList.add(clients);
        course.setListeners(listenerList);
        course.setSpeaker(speaker);
        List<Comment> comments = new LinkedList<>();
        comments.add(comment);
        course.setComments(comments);
        course.setCourseStatus(courseStatus);
        for (Lesson lesson1 : lessons) {
            if (lesson1 != null) {
                price = lesson1.getPrice() + price;
            }
        }
        course.setPrice(price);
        courseDao.save(course);


    }

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public void update(Course course) {
        courseDao.save(course);
    }

    @Override
    public void delete(Long id) {
        courseDao.delete(courseDao.getById(id));
    }

    @Override
    public Course findById(Long id) {
        return courseDao.getById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.findAll();
    }

    @Override
    public void addListener(Course course, Role.Profile client) {
        Course course1 = courseDao.getById(course.getId());
        List<Role.Profile> listenerList = course1.getListeners();
        boolean b = true;
        for (int i = 0; i < course1.getListeners().size(); i++) {
            Role.Profile listener = listenerList.get(i);
            if (listener.getUsername().equals(client.getUsername())) {
                b = false;
            }
        }
        if (b) {
            listenerList.add(profileDao.findByUsername(client.getUsername()));
            course1.setListeners(listenerList);
            update(course1);
        }
    }

    @Override
    public void deleteListener(Course course, Role.Profile client) {
        Course course1 = courseDao.getById(course.getId());
        List<Role.Profile> listenerList = course1.getListeners();
        for (int i = 0; i < listenerList.size(); i++) {
            Role.Profile client1 = listenerList.get(i);
            if (client1.getUsername().equals(client.getUsername())) {
                listenerList.remove(i);
            }
        }
        course1.setListeners(listenerList);
        update(course1);
    }

    @Override
    public void addLesson(Course course, Lesson lesson) {
        Course course1 = courseDao.getById(course.getId());
        List<Lesson> lessonList = course1.getLessons();
        boolean b = true;
        for (int i = 0; i < lessonList.size(); i++) {
            Lesson lesson1 = lessonList.get(i);
            if (lesson1.getName().equals(lesson.getName())) {
                b = false;
            }
        }
        if (b) {
            lessonList.add(lessonDao.getById(lesson.getId()));
            course1.setLessons(lessonList);
            update(course1);
        }
        course1.getLessons().add(lesson);
        update(course1);
    }

    @Override
    public void addComment(Course course, Comment comment) {
        Course course1 = courseDao.getById(course.getId());
        course1.getComments().add(comment);
        update(course1);
    }

    @Override
    public List<Course> getCourseByStatus(CourseStatus courseStatus) {
        List<Course> coursesStatus = new LinkedList<>();
        List<Course> courses = courseDao.findAll();
        for (Course co : courses) {
            if (co.getCourseStatus() == courseStatus) {
                coursesStatus.add(co);
            }
        }
        return coursesStatus.stream().sorted((o1, o2) -> o1.getStartCourse().compareTo(o2.getStartCourse())).collect(Collectors.toList());

    }

    @Override
    public List<Course> getCourseAfterThisDate(LocalDate localDate) {
        List<Course> coursesDate = new LinkedList<>();
        List<Course> courses = courseDao.findAll();
        for (Course co :
                courses) {
            if (co.getStartCourse().isAfter(localDate)) {
                coursesDate.add(co);
            }
        }
        return coursesDate.stream().sorted((o1, o2) -> o1.getStartCourse().compareTo(o2.getStartCourse())).collect(Collectors.toList());

    }

    @Override
    public void changeSpeaker(Course course, Role.Profile speaker) {
        if (speaker.getStatus().equals(ProfileStatus.SPEAKER)) {
            course.setSpeaker(speaker);
            courseDao.save(course);
        }
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> coursesStatus = new LinkedList<>();
        List<Course> courses = courseDao.findAll();
        for (Course co :
                courses) {
            if (co.getName().contains(name)) {
                coursesStatus.add(co);
            }
        }
        return coursesStatus.stream().sorted((o1, o2) -> o1.getStartCourse().compareTo(o2.getStartCourse())).collect(Collectors.toList());
    }
}
