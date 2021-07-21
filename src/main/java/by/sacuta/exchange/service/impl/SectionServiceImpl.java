package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.SectionDao;
import by.sacuta.exchange.exception.MyServiceException;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Section;
import by.sacuta.exchange.service.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SectionServiceImpl.class);
    private final SectionDao sectionDao;

    public SectionServiceImpl(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Override
    public List<Section> getAll() {

        try {
            LOGGER.info("get all section");
            return sectionDao.findAll();
        } catch (MyServiceException e) {
            LOGGER.warn("get all section failed ", e);
            throw new MyServiceException("get all section failed ", e);
        }
    }

    @Override
    public Section getById(Long id) {
        try {
            LOGGER.info("findByID section  " + id);
            return sectionDao.getById(id);
        } catch (MyServiceException e) {
            LOGGER.warn("findByID section failed " + id, e);
            throw new MyServiceException("findByID section failed " + id, e);
        }
    }

    @Override
    public void createNewSection(String name) {
        try {
            LOGGER.info("create new section  " + name);
            sectionDao.save(new Section(name));
        } catch (MyServiceException e) {
            LOGGER.warn("create new section failed " + name, e);
            throw new MyServiceException("create new section failed " + name, e);
        }
    }

    @Override
    public void save(Section section) {
        try {
            LOGGER.info("save section  " + section.getName());
            sectionDao.save(section);
        } catch (MyServiceException e) {
            LOGGER.warn("save section failed " + section.getName(), e);
            throw new MyServiceException("save section failed " + section.getName(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            LOGGER.info("delete section  " + id);
            sectionDao.delete(sectionDao.getById(id));
        } catch (MyServiceException e) {
            LOGGER.warn("delete section failed " + id, e);
            throw new MyServiceException("delete section failed " + id, e);
        }
    }

    @Override
    public void update(Section section) {
        try {
            LOGGER.info("update section  " + section.getId());
            sectionDao.save(section);
        } catch (MyServiceException e) {
            LOGGER.warn("update section failed " + section.getId(), e);
            throw new MyServiceException("update section failed " + section.getId(), e);
        }
    }

    @Override
    public List<Course> getAllCourse(Section section) {
        try {
            LOGGER.info("getAllCourse section  " + section.getId());
            return section.getCourseList();
        } catch (MyServiceException e) {
            LOGGER.warn("getAllCourse section failed " + section.getId(), e);
            throw new MyServiceException("getAllCourse section failed " + section.getId(), e);
        }
    }
}
