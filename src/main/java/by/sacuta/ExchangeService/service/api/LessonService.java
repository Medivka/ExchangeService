package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.Lesson;
import by.sacuta.ExchangeService.model.enums.LessonStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface LessonService {

    void createNewLesson(String name, LocalDateTime localDateTime, Integer duration, LessonStatus lessonStatus, Integer price);

    void save(Lesson lesson);

    void delete(Long id);

    void update(Lesson lesson);

    Lesson findById(Long id);

    List<Lesson> getAll();

    List<Lesson> findByName(String name);

    List<Lesson> findByDate(LocalDateTime localDateTime);

    List<Lesson> findByStatus(LessonStatus lessonStatus);
}
