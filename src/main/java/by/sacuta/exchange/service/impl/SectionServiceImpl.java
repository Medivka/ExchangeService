package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.SectionDao;
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
        LOGGER.info("get all section");
        return sectionDao.findAll();
    }

    @Override
    public Section getById(Long id) {
        LOGGER.info("findByID section  " + id);
        return sectionDao.getById(id);
    }

    @Override
    public void createNewSection(String name) {
        LOGGER.info("create new section  " + name);
        sectionDao.save(new Section(name));
    }

    @Override
    public void save(Section section) {
        LOGGER.info("save section  " + section.getName());
        sectionDao.save(section);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete section  " + id);
        sectionDao.delete(sectionDao.getById(id));
    }

    @Override
    public void update(Section section) {
        LOGGER.info("update section  " + section.getId());
        Section sectionInDB = sectionDao.getById(section.getId());
        sectionInDB.setName(section.getName());
        sectionDao.save(sectionInDB);
    }

    @Override
    public List<Course> getAllCourse(Section section) {
        LOGGER.info("getAllCourse section  " + section.getId());
        return section.getCourseList();
    }
}
