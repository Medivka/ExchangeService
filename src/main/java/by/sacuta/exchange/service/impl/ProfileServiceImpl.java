package by.sacuta.exchange.service.impl;

import by.sacuta.exchange.dao.CourseDao;
import by.sacuta.exchange.dao.ProfileDao;
import by.sacuta.exchange.dao.RoleDao;
import by.sacuta.exchange.domain.enums.ProfileStatus;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.service.ProfileService;
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
public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);
    private final ProfileDao profileDao;
    private final RoleDao roleDao;
    private final CourseDao courseDao;

    public ProfileServiceImpl(ProfileDao profileDao, RoleDao roleDao, CourseDao courseDao) {
        this.profileDao = profileDao;
        this.roleDao = roleDao;
        this.courseDao = courseDao;
    }


    @Override
    public boolean existsByUsername(String username) {
        if (findByUsername(username) == null) {
            return false;
        } else return true;
    }

    @Override
    public Profile findByUsername(String username) {
        LOGGER.info("find by username:  " + username);
        return profileDao.findByUsername(username);
    }

    @Override
    public void createNewProfile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status) {
        Profile profile = new Profile(username, password, name, lastname, age, email, city, status);
        LOGGER.info("create new profile  " + username);
        save(profile);
    }

    @Override
    public void save(Profile profile) {
        if (!existsByUsername(profile.getUsername())) {
            profileDao.save(profile);
            LOGGER.info(String.format("save profile : %s  username: %s", profile.getId(), profile.getUsername()));
        }
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("delete profile  " + id);
        profileDao.delete(profileDao.getById(id));
    }

    @Override
    public void update(Profile profile) {

        LOGGER.info("update profile  " + profile.getId());
        Profile profileInDB = profileDao.getById(profile.getId());
        profileInDB.setUsername(profile.getUsername());
        profileInDB.setName(profile.getName());
        profileInDB.setLastname(profile.getLastname());
        profileInDB.setPassword(profile.getPassword());
        profileInDB.setEmail(profile.getEmail());
        profileInDB.setStatus(profile.getStatus());
        profileInDB.setCity(profile.getCity());
        profileInDB.setAge(profile.getAge());
        profileDao.save(profileInDB);
    }

    @Override
    public Profile findByID(Long id) {
        LOGGER.info("findByID profile  " + id);
        return profileDao.getById(id);
    }

    @Override
    public List<Profile> getAll() {
        LOGGER.info("getAll profile  ");
        return profileDao.findAll();
    }


    @Override
    public List<Course> getAllMyCourse(String username) {
        LOGGER.info("get all my course profile  " + username);
        List<Course> myCourses = new LinkedList<>();
        List<Course> allCourses = courseDao.findAll();
        for (Course co : allCourses) {
            List<Profile> profileList = co.getListeners();
            for (Profile cli : profileList) {
                if (cli.getUsername().equals(username)) {
                    myCourses.add(co);
                }
            }
        }
        return myCourses.stream().sorted((o1, o2) -> o1.getStartCourse().compareTo(o2.getStartCourse())).collect(Collectors.toList());
    }

    @Override
    public List<Lesson> getActualLesson(String username) {
        LOGGER.info("getActualLesson profile " + username);
        List<Lesson> lessons = new LinkedList<>();
        List<Course> courses = getAllMyCourse(username);
        for (Course co : courses) {
            List<Lesson> lessons1 = co.getLessons();
            for (Lesson le : lessons1) {
                if (le.getLocalDateTime().isAfter(LocalDateTime.now())) {
                    lessons.add(le);
                }
            }
        }
        return lessons.stream().sorted((a, b) -> a.getLocalDateTime().compareTo(b.getLocalDateTime())).collect(Collectors.toList());
    }

    @Override
    public List<Profile> getAllSpeaker() {

        LOGGER.info("getAllSpeaker profile ");
        List<Profile> speakers = new LinkedList<>();
        for (Profile prof : profileDao.findAll()
        ) {
            if (prof.getStatus().equals(ProfileStatus.SPEAKER)) {
                speakers.add(prof);
            }
        }
        return speakers;
    }

    @Override
    public void getRoleAdmin(Profile profile) {
        profile.getRoles().add(roleDao.getById(2l));
    }
}
