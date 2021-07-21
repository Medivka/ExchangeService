package by.sacuta.exchange.service;


import by.sacuta.exchange.domain.model.Section;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionServiceImplTest {

    @Resource
    SectionService  sectionService;
    @Test
    public void getAll() {
        Assert.assertNotNull(sectionService.getAll());
    }

    @Test
    public void getById() {
        Long id = 1l;
        Section section = sectionService.getById(id);
        Assert.assertNotNull(section);
        Long id2 = section.getId();
        Assert.assertEquals(id, id2);
    }

    @Test
    public void createNewSection() {

    }

    @Test
    @Transactional
    public void update() {
        Long id = 1l;
        String massage = "update";
        Section section = sectionService.getById(id);
        section.setName(massage);
        sectionService.update(section);
        Assert.assertEquals(massage, sectionService.getById(id).getName());
    }

    @Test
    public void getAllCourse() {
        Assert.assertNotNull(sectionService.getAll());
    }
}