package by.sacuta.exchangeService.service;

import by.sacuta.exchangeService.dao.CourseDao;
import by.sacuta.exchangeService.dao.ProfileDao;
import by.sacuta.exchangeService.dao.RoleDao;
import by.sacuta.exchangeService.exception.MyServiceException;
import by.sacuta.exchangeService.model.model.Course;
import by.sacuta.exchangeService.model.model.Lesson;
import by.sacuta.exchangeService.model.model.Profile;
import by.sacuta.exchangeService.model.model.Role;
import by.sacuta.exchangeService.model.enums.ProfileStatus;
import by.sacuta.exchangeService.service.api.ProfileService;
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
    public Profile findByUsername(String username) {
        try {
            LOGGER.info("find by username:  " + username);
            return profileDao.findByUsername(username);
        } catch (MyServiceException e) {
            LOGGER.warn("find by username failed " + username, e);
            throw new MyServiceException("find by username failed " + username, e);
        }
    }

    @Override
    public void createNewProfile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status) {
        try {
            Profile client = new Profile(username, password, name, lastname, age, email, city, status);
            LOGGER.info("create new profile  " + username);
            save(client);
        } catch (MyServiceException e) {
            LOGGER.warn("create new profile failed " + username, e);
            throw new MyServiceException("create new profile failed " + username, e);
        }
    }

    @Override
    public void save(Profile profile) {
        try {
            List<Profile> profileList = profileDao.findAll();
            boolean b = true;
            for (int i = 0; i < profileList.size(); i++) {
                Profile prof = profileList.get(i);
                if (prof.getUsername().equals(profile.getUsername())) {
                    b = false;
                }
            }
            if (b) {
                List<Role> roles = new LinkedList<>();
                roles.add(roleDao.getById(1l));
                profile.setRoles(roles);
                profileDao.save(profile);
                LOGGER.info("save profile  " + profile.getId());
            }
        } catch (MyServiceException e) {
            LOGGER.warn("save profile failed " + profile.getId(), e);
            throw new MyServiceException("save profile failed " + profile.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            LOGGER.info("delete profile  " + id);
            profileDao.delete(profileDao.getById(id));
        } catch (MyServiceException e) {
            LOGGER.warn("delete profile failed " + id, e);
            throw new MyServiceException("delete profile failed " + id, e);
        }
    }

    @Override
    public void update(Profile profile) {
        try {
            LOGGER.info("update profile  " + profile.getId());
            profileDao.save(profile);
        } catch (MyServiceException e) {
            LOGGER.warn("update profile failed " + profile.getId(), e);
            throw new MyServiceException("update profile failed " + profile.getId(), e);
        }
    }

    @Override
    public Profile findByID(Long id) {
        try {
            LOGGER.info("findByID profile  " + id);
            return profileDao.getById(id);
        } catch (MyServiceException e) {
            LOGGER.warn("findByID profile failed " + id, e);
            throw new MyServiceException("findByID profile failed " + id, e);
        }
    }

    @Override
    public List<Profile> getAll() {
        try {
            LOGGER.info("getAll profile  ");
            return profileDao.findAll();
        } catch (MyServiceException e) {
            LOGGER.warn("getAll profile failed ", e);
            throw new MyServiceException("getAll profile failed ", e);
        }
    }


    @Override
    public List<Course> getAllMyCourse(String username) {
        try {
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
            myCourses.stream().sorted((o1, o2) -> o1.getStartCourse().compareTo(o2.getStartCourse())).collect(Collectors.toList());
            return myCourses;
        } catch (MyServiceException e) {
            LOGGER.warn("get all my course profile failed " + username, e);
            throw new MyServiceException("get all my course profile  failed" + username, e);
        }
    }

    @Override
    public List<Lesson> getActualLesson(String username) {
        try {
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
        } catch (MyServiceException e) {
            LOGGER.warn("getActualLesson profile failed " + username, e);
            throw new MyServiceException("getActualLesson profile  failed" + username, e);
        }
    }

    @Override
    public List<Profile> getAllSpeaker() {
        try {
            LOGGER.info("getAllSpeaker profile ");
            List<Profile> speakers = new LinkedList<>();
            for (Profile prof : profileDao.findAll()
            ) {
                if (prof.getStatus().equals(ProfileStatus.SPEAKER)) {
                    speakers.add(prof);
                }
            }
            return speakers;
        } catch (MyServiceException e) {
            LOGGER.warn("getAllSpeaker failed ", e);
            throw new MyServiceException("getAllSpeaker  failed", e);
        }
    }
}
