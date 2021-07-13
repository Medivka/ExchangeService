package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.dao.LessonDao;
import by.sacuta.ExchangeService.model.Lesson;
import by.sacuta.ExchangeService.model.enums.LessonStatus;
import by.sacuta.ExchangeService.service.api.LessonService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public void createNewLesson(String name, LocalDateTime localDateTime, Integer duration, LessonStatus lessonStatus, Integer price) {
              lessonDao.save(new Lesson(name, localDateTime,duration ,lessonStatus, price));
    }

    @Override
    public void save(Lesson lesson) {
        lessonDao.save(lesson);

    }

    @Override
    public void delete(Long id) {
        lessonDao.delete(lessonDao.getById(id));

    }

    @Override
    public void update(Lesson lesson) {
        lessonDao.save(lesson);

    }

    @Override
    public Lesson findById(Long id) {
        return lessonDao.getById(id);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonDao.findAll();
    }

    @Override
    public List<Lesson> findByName(String name) {
        List<Lesson> lessons = new LinkedList<>();
        for (Lesson ls : lessonDao.findAll()
        ) {
            if (ls.getName().contains(name)) {
                lessons.add(ls);
            }
        }
        return lessons;
    }

    @Override
    public List<Lesson> findByDate(LocalDateTime localDateTime) {
        List<Lesson> lessons = new LinkedList<>();
        for (Lesson ls : lessonDao.findAll()
        ) {
            if (ls.getLocalDateTime().isEqual(localDateTime) && ls.getLocalDateTime().isBefore(localDateTime)) {
                lessons.add(ls);
            }
        }
        return lessons;
    }

    @Override
    public List<Lesson> findByStatus(LessonStatus lessonStatus) {
        List<Lesson> lessons = new LinkedList<>();
        for (Lesson ls : lessonDao.findAll()
        ) {
            if (ls.getLessonStatus() == lessonStatus) {
                lessons.add(ls);
            }
        }
        return lessons;
    }
}
