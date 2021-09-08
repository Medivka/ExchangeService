package by.sacuta.exchange.web.rest;

import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Section;
import by.sacuta.exchange.dto.CourseDTO;
import by.sacuta.exchange.dto.SectionDTO;
import by.sacuta.exchange.service.MyModelMapper;
import by.sacuta.exchange.service.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/section")
public class SectionRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionRestController.class);

    private final SectionService sectionService;
    private final MyModelMapper myModelMapper;

    public SectionRestController(SectionService sectionService, MyModelMapper myModelMapper) {
        this.sectionService = sectionService;
        this.myModelMapper = myModelMapper;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SectionDTO>> read() {
        LOGGER.info("rest/section/get/all ");
        final List<SectionDTO> sectionDTOS = new LinkedList<>();
        for (Section section : sectionService.getAll()
        ) {
            sectionDTOS.add(myModelMapper.mapToSectionDTO(section));
        }
        return sectionDTOS != null && !sectionDTOS.isEmpty()
                ? new ResponseEntity<>(sectionDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SectionDTO> read(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/section/get/{%s} ",id));
        final SectionDTO sectionDTO = myModelMapper.mapToSectionDTO(sectionService.getById(id));
        return sectionDTO != null
                ? new ResponseEntity<>(sectionDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/getAllCourse/{id}")
    public ResponseEntity<List<CourseDTO>> getAllCourse(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/section/getAllCourse/{%s} ",id));
        final List<CourseDTO> courseDTOS = new LinkedList<>();
        for (Course course : sectionService.getAllCourse(sectionService.getById(id))
        ) {
            courseDTOS.add(myModelMapper.mapToCourseDTO(course));
        }
        return courseDTOS != null && !courseDTOS.isEmpty()
                ? new ResponseEntity<>(courseDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> create(@RequestBody SectionDTO sectionDTO) {
        LOGGER.info(String.format( "rest/section/save/{%s} ",sectionDTO.getName()));
        sectionService.save(myModelMapper.mapToSection(sectionDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody SectionDTO sectionDTO) {
        LOGGER.info(String.format( "rest/section/update/{%s} ",id));
        boolean update = false;
        final List<Section> sections = sectionService.getAll();
        for (Section section : sections) {
            if (section.getId() == (id)) {
                section = myModelMapper.mapToSection(sectionDTO);
                update = true;
                sectionService.update(section);
            }
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/section/delete/{%s} ",id));
        boolean delete = false;
        final List<Section> sections = sectionService.getAll();
        for (Section section : sections) {
            if (section.getId() == (id)) {
                delete = true;
                sectionService.delete(id);
            }
        }
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
