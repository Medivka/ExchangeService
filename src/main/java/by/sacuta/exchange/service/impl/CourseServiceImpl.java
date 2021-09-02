package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.CommentDao;
import by.sacuta.exchange.dao.CourseDao;
import by.sacuta.exchange.dao.LessonDao;
import by.sacuta.exchange.dao.ProfileDao;
import by.sacuta.exchange.domain.enums.CourseStatus;
import by.sacuta.exchange.domain.enums.ProfileStatus;
import by.sacuta.exchange.domain.model.*;
import by.sacuta.exchange.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

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
    public void createNewCourse(String name, Section section, LocalDate startDate, Integer days, Profile speaker, CourseStatus courseStatus, Lesson lesson, Profile profile, Comment comment) {

        Integer price = 0;
        Course course = new Course();
        course.setSection(section);
        course.setName(name);
        course.setStartCourse(startDate);
        course.setDays(days);
        course.setEndCourse(startDate.plusDays(days));
        List<Lesson> lessons = new LinkedList<>();
        lessons.add(lesson);
        course.setLessons(lessons);
        List<Profile> listenerList = new LinkedList<>();
        listenerList.add(profile);
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
        LOGGER.info("create new course ");

    }

    @Override
    public void save(Course course) {
        LOGGER.info("save course " + course.getId());
        courseDao.save(course);

    }

    @Override
    public void update(Course course) {
        LOGGER.info("update course " + course.getId());
        Course courseInDB = courseDao.getById(course.getId());
        courseInDB.setName(course.getName());
        courseInDB.setStartCourse(course.getStartCourse());
        courseInDB.setEndCourse(course.getEndCourse());
        courseInDB.setDays(course.getDays());
        courseInDB.setPrice(course.getPrice());
        courseInDB.setSection(course.getSection());
        courseInDB.setSpeaker(course.getSpeaker());
        courseInDB.setCourseStatus(course.getCourseStatus());
        courseDao.save(courseInDB);

    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete course " + id);
        courseDao.delete(courseDao.getById(id));

    }

    @Override
    public Course findById(Long id) {
        LOGGER.info("find  course by id " + id);
        return courseDao.getById(id);

    }

    @Override
    public List<Course> getAll() {
        LOGGER.info("get all course ");
        return courseDao.findAll();
    }

    @Override
    public boolean existProfileInCourse(Course course, Profile profile) {
        LOGGER.info(String.format("exist Profile:%s In Course: %s ", profile.getId(), course.getId()));
        for (Profile profile1 : course.getListeners()) {
            if (profile1.getUsername().equals(profile.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addListener(Course course, Profile profile) {
        if (!existProfileInCourse(course, profile)) {
            Course course1 = courseDao.getById(course.getId());
            List<Profile> listenerList = course1.getListeners();
            listenerList.add(profileDao.findByUsername(profile.getUsername()));
            course1.setListeners(listenerList);
            update(course1);
            LOGGER.info(String.format("add listener: %s  to course:%s", profile.getId(), course.getId()));
        }

    }

    @Override
    public void deleteListener(Course course, Profile profile) {
        LOGGER.info(String.format("delete listener: %s  to course:%s", profile.getId(), course.getId()));
        Course courseFromDB = courseDao.getById(course.getId());
        List<Profile> listenerList = courseFromDB.getListeners();
        for (int i = 0; i < listenerList.size(); i++) {
            Profile client1 = listenerList.get(i);
            if (client1.getUsername().equals(profile.getUsername())) {
                listenerList.remove(i);
            }
        }
        courseFromDB.setListeners(listenerList);
        update(courseFromDB);


    }

    @Override
    public boolean existByLessonName(Course course, Lesson lesson) {
        LOGGER.info(String.format("exist Lesson:%s In Course: %s ", lesson.getId(), course.getId()));
        for (int i = 0; i < course.getLessons().size(); i++) {
            Lesson lesson1 = course.getLessons().get(i);
            if (lesson1.getName().equals(lesson.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addLesson(Course course, Lesson lesson) {
        lesson.setCourse(course);
        LOGGER.info(String.format("add lesson: %s  to course:%s", lesson.getId(), course.getId()));

    }

    @Override
    public void addComment(Course course, Comment comment) {
        Course course1 = courseDao.getById(course.getId());
        course1.getComments().add(comment);
        update(course1);
        LOGGER.info(String.format("add comment: %s  to course: %s", comment.getId(), course.getId()));
    }

    @Override
    public List<Course> getCourseByStatus(CourseStatus courseStatus) {
        LOGGER.info("get course by status: " + courseStatus);
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
        LOGGER.info("get course by localDate: " + localDate);
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
    public void changeSpeaker(Course course, Profile speaker) {
        LOGGER.info(String.format("change speaker: %s  to course:%s", speaker.getId(), course.getId()));
        if (speaker.getStatus().equals(ProfileStatus.SPEAKER)) {
            course.setSpeaker(speaker);
            courseDao.save(course);
        }
    }

    @Override
    public List<Course> findByName(String name) {
        LOGGER.info(String.format("find by name: %s   ", name));
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
