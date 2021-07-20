package by.sacuta.exchangeService.restController;

import by.sacuta.exchangeService.model.dto.CourseDTO;
import by.sacuta.exchangeService.model.dto.SectionDTO;
import by.sacuta.exchangeService.model.model.Course;
import by.sacuta.exchangeService.model.model.Section;
import by.sacuta.exchangeService.service.api.MyModelMapper;
import by.sacuta.exchangeService.service.api.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/section")
public class SectionRestController {

    private final SectionService sectionService;
    private final MyModelMapper myModelMapper;

    public SectionRestController(SectionService sectionService, MyModelMapper myModelMapper) {
        this.sectionService = sectionService;
        this.myModelMapper = myModelMapper;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<SectionDTO>> read() {
        final List<SectionDTO> sectionDTOS = new LinkedList<>();
        for (Section section : sectionService.getAll()
        ) {
            sectionDTOS.add(myModelMapper.mapToSectionDTO(section));
        }
        return sectionDTOS != null && !sectionDTOS.isEmpty()
                ? new ResponseEntity<>(sectionDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<SectionDTO> read(@PathVariable(name = "id") long id) {
        final SectionDTO sectionDTO = myModelMapper.mapToSectionDTO(sectionService.getById(id));
        return sectionDTO != null
                ? new ResponseEntity<>(sectionDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/getAllCourse/{id}")
    public ResponseEntity<List<CourseDTO>> getAllCourse(@PathVariable(name = "id") long id) {
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
        sectionService.save(myModelMapper.mapToSection(sectionDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody SectionDTO sectionDTO) {
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

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
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
