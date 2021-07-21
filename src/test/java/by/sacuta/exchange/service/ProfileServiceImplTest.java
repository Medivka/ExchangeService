package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.Profile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceImplTest {
    @Resource
    ProfileService profileService;

    @Test
    public void findByUsername() {
        Assert.assertNotNull(profileService.findByUsername("admin"));
    }

    @Test
    @Transactional
    public void update() {;
        Profile clients = profileService.findByID(1l);
        clients.setName("adminki");
        profileService.update(clients);
        Assert.assertEquals(profileService.findByID(1l).getName(), "adminki");
    }

    @Test
    public void findByID() {
        Long id = 1l;
        Profile profile = profileService.findByID(id);
        Assert.assertNotNull(profile);
        Long id2 = profile.getId();
        Assert.assertEquals(id, id2);
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(profileService.getAll());
    }

    @Test
    public void getByUsername() {
        Assert.assertNotNull(profileService.findByUsername("user"));
    }

    @Test
    public void getAllMyCourse() {
        Assert.assertNotNull(profileService.getAllMyCourse("user"));
    }

    @Test
    public void getActualLesson() {
        Assert.assertNotNull(profileService.getActualLesson("user"));

    }

    @Test
    public void getAllSpeaker() {
        Assert.assertNotNull(profileService.getAllSpeaker());
    }
}