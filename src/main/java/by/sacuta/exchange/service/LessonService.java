package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.domain.enums.LessonStatus;

import java.time.LocalDateTime;
import java.util.List;


public interface LessonService {

    void createNewLesson(String name, Course course, LocalDateTime localDateTime, Integer duration, LessonStatus lessonStatus, Integer price);

    void save(Lesson lesson);

    void delete(Long id);

    void update(Lesson lesson);

    Lesson findById(Long id);

    List<Lesson> getAll();

    List<Lesson> findByName(String name);

    List<Lesson> findByDate(LocalDateTime localDateTime);

    List<Lesson> findByStatus(LessonStatus lessonStatus);
}
