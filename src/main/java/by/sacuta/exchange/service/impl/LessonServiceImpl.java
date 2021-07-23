package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.LessonDao;
import by.sacuta.exchange.exception.MyServiceException;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.domain.enums.LessonStatus;
import by.sacuta.exchange.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
        try {
            LOGGER.info("create new lesson ");
            lessonDao.save(new Lesson(name, course, localDateTime, duration, lessonStatus, price));
        } catch (MyServiceException e) {
            LOGGER.warn("create new lesson failed ", e);
            throw new MyServiceException("create new lesson failed ", e);
        }
    }

    @Override
    public void save(Lesson lesson) {
        try {
            LOGGER.info("save lesson " + lesson.getId());
            lessonDao.save(lesson);
        } catch (MyServiceException e) {
            LOGGER.warn("save lesson failed ", e);
            throw new MyServiceException("save lesson failed ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            LOGGER.info("delete lesson " + id);
            lessonDao.delete(lessonDao.getById(id));
        } catch (MyServiceException e) {
            LOGGER.warn("delete lesson failed " + id, e);
            throw new MyServiceException("delete lesson failed " + id, e);
        }
    }

    @Override
    public void update(Lesson lesson) {
        try {
            LOGGER.info("update lesson " + lesson.getId());
            lessonDao.save(lesson);
        } catch (MyServiceException e) {
            LOGGER.warn("update lesson failed " + lesson.getId(), e);
            throw new MyServiceException("update lesson failed " + lesson.getId(), e);
        }
    }

    @Override
    public Lesson findById(Long id) {
        try {
            LOGGER.info("findById lesson " + id);
            return lessonDao.getById(id);
        } catch (MyServiceException e) {
            LOGGER.warn("findById lesson failed " + id, e);
            throw new MyServiceException("findById lesson failed " + id, e);
        }
    }

    @Override
    public List<Lesson> getAll() {
        try {
            LOGGER.info("getAll lesson ");
            return lessonDao.findAll();
        } catch (MyServiceException e) {
            LOGGER.warn("getAll lesson failed ", e);
            throw new MyServiceException("getAll lesson failed ", e);
        }
    }

    @Override
    public List<Lesson> findByName(String name) {
        try {
            LOGGER.info("findByName lesson: " + name);
            List<Lesson> lessons = new LinkedList<>();
            for (Lesson ls : lessonDao.findAll()
            ) {
                if (ls.getName().contains(name)) {
                    lessons.add(ls);
                }
            }
            return lessons;
        } catch (MyServiceException e) {
            LOGGER.warn("findByName lesson failed " + name, e);
            throw new MyServiceException("findByName lesson failed " + name, e);
        }
    }

    @Override
    public List<Lesson> findByDate(LocalDateTime localDateTime) {
        try {
            LOGGER.info("findByDate lesson: " + localDateTime);
            List<Lesson> lessons = new LinkedList<>();
            for (Lesson lesson : lessonDao.findAll()
            ) {
                if ((lesson.getLocalDateTime().isEqual(localDateTime)) || (lesson.getLocalDateTime().isAfter(localDateTime))) {
                    lessons.add(lesson);
                }
            }
            return lessons;
        } catch (MyServiceException e) {
            LOGGER.warn("findByDate lesson failed " + localDateTime, e);
            throw new MyServiceException("findByDate lesson failed " + localDateTime, e);
        }
    }

    @Override
    public List<Lesson> findByStatus(LessonStatus lessonStatus) {
        try {
            LOGGER.info("findByStatus lesson: " + lessonStatus);
            List<Lesson> lessons = new LinkedList<>();
            for (Lesson ls : lessonDao.findAll()
            ) {
                if (ls.getLessonStatus() == lessonStatus) {
                    lessons.add(ls);
                }
            }
            return lessons;
        } catch (MyServiceException e) {
            LOGGER.warn("findByStatus lesson failed " + lessonStatus, e);
            throw new MyServiceException("findByStatus lesson failed " + lessonStatus, e);
        }
    }
}
