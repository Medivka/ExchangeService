package by.sacuta.exchangeService.service.api;

import by.sacuta.exchangeService.model.model.Course;
import by.sacuta.exchangeService.model.model.Lesson;
import by.sacuta.exchangeService.model.enums.LessonStatus;

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
