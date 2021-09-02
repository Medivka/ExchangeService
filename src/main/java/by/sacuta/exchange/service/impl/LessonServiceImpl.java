package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.LessonDao;
import by.sacuta.exchange.domain.enums.LessonStatus;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.exception.MyServiceException;
import by.sacuta.exchange.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LessonServiceImpl.class);
    private final LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public void createNewLesson(String name, Course course, LocalDateTime localDateTime, Integer duration, LessonStatus lessonStatus, Integer price) {
        LOGGER.info("create new lesson ");
        lessonDao.save(new Lesson(name, course, localDateTime, duration, lessonStatus, price));

    }

    @Override
    public void save(Lesson lesson) {
        LOGGER.info("save lesson " + lesson.getId());
        lessonDao.save(lesson);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete lesson " + id);
        lessonDao.delete(lessonDao.getById(id));
    }

    @Override
    public void update(Lesson lesson) {
        LOGGER.info("update lesson " + lesson.getId());
        Lesson les = lessonDao.getById(lesson.getId());
        les.setName(lesson.getName());
        les.setLocalDateTime(lesson.getLocalDateTime());
        les.setLessonStatus(lesson.getLessonStatus());
        les.setDuration(lesson.getDuration());
        les.setPrice(lesson.getPrice());
        lessonDao.save(les);
    }

    @Override
    public Lesson findById(Long id) {
        if (lessonDao.existsById(id)) {
            LOGGER.info("findById lesson " + id);
            return lessonDao.getById(id);
        } else {
            throw new MyServiceException("lesson not found");
        }

    }

    @Override
    public List<Lesson> getAll() {
        LOGGER.info("getAll lesson ");
        return lessonDao.findAll();

    }

    @Override
    public List<Lesson> findByName(String name) {
        LOGGER.info("findByName lesson: " + name);
        List<Lesson> lessons = new LinkedList<>();
        for (Lesson ls : lessonDao.findAll()
        ) {
            if (ls.getName().contains(name)) {
                lessons.add(ls);
            }
        }
        return lessons.stream().sorted((o1, o2) -> o1.getLocalDateTime().compareTo(o2.getLocalDateTime())).collect(Collectors.toList());
    }

    @Override
    public List<Lesson> findByDate(LocalDateTime localDateTime) {
        LOGGER.info("findByDate lesson: " + localDateTime);
        List<Lesson> lessons = new LinkedList<>();
        for (Lesson lesson : lessonDao.findAll()
        ) {
            if ((lesson.getLocalDateTime().isEqual(localDateTime)) || (lesson.getLocalDateTime().isAfter(localDateTime))) {
                lessons.add(lesson);
            }
        }
        return lessons.stream().sorted((o1, o2) -> o1.getLocalDateTime().compareTo(o2.getLocalDateTime())).collect(Collectors.toList());
    }

    @Override
    public List<Lesson> findByStatus(LessonStatus lessonStatus) {
        LOGGER.info("findByStatus lesson: " + lessonStatus);
        List<Lesson> lessons = new LinkedList<>();
        for (Lesson ls : lessonDao.findAll()
        ) {
            if (ls.getLessonStatus() == lessonStatus) {
                lessons.add(ls);
            }
        }
        return lessons.stream().sorted((o1, o2) -> o1.getLocalDateTime().compareTo(o2.getLocalDateTime())).collect(Collectors.toList());
    }
}
