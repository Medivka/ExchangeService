package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.model.*;
import by.sacuta.ExchangeService.model.enums.CourseStatus;

import java.time.LocalDate;
import java.util.List;


public interface CourseService {
    void createNewCourse(String name, LocalDate startDate, Integer days, Profile speaker, CourseStatus courseStatus, Lesson lesson, Profile clients, Comment comment);

    void save(Course course);

    void update(Course course);

    void delete(Long id);

    Course findById(Long id);

    List<Course> getAll();

    void addListener(Course course, Profile client);

    void deleteListener(Course course, Profile client);

    void addLesson(Course course, Lesson lesson);

    void addComment(Course course, Comment comment);

    List<Course> getCourseByStatus(CourseStatus courseStatus);

    List<Course> getCourseAfterThisDate(LocalDate localDate);

    void changeSpeaker(Course course, Profile speaker);

    List<Course> findByName(String name);
}
