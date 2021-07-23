package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.*;
import by.sacuta.exchange.domain.enums.CourseStatus;

import java.time.LocalDate;
import java.util.List;


public interface CourseService {

    void createNewCourse(String name, Section section, LocalDate startDate, Integer days, Profile speaker, CourseStatus courseStatus, Lesson lesson, Profile profile, Comment comment);

    void save(Course course);

    void update(Course course);

    void delete(Long id);

    Course findById(Long id);

    List<Course> getAll();

    void addListener(Course course, Profile profile);

    void deleteListener(Course course, Profile profile);

    boolean existByLessonName(Course course, Lesson lesson);

    void addLesson(Course course, Lesson lesson);

    void addComment(Course course, Comment comment);

    List<Course> getCourseByStatus(CourseStatus courseStatus);

    List<Course> getCourseAfterThisDate(LocalDate localDate);

    void changeSpeaker(Course course, Profile speaker);

    List<Course> findByName(String name);
}
