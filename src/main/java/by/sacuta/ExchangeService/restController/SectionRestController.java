package by.sacuta.ExchangeService.restController;

import by.sacuta.ExchangeService.model.dto.CourseDTO;
import by.sacuta.ExchangeService.model.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.dto.SectionDTO;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.model.model.Section;
import by.sacuta.ExchangeService.service.api.MyModelMapper;
import by.sacuta.ExchangeService.service.api.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
@RestController
@RequestMapping("rest/section")
public class SectionRestController {
  private final   SectionService sectionService;
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

    @GetMapping(value = "/get/{id}}")
    public ResponseEntity<List<CourseDTO>>getCourse(@PathVariable(name = "id") long id) {
        final List<CourseDTO> courseDTOS = new LinkedList<>();
        for (Course course : sectionService.getAllCourse(sectionService.getById(id))
        ) {
            courseDTOS.add(myModelMapper.mapToCourseDTO(course));
        }
        return courseDTOS != null && !courseDTOS.isEmpty()
                ? new ResponseEntity<>(courseDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
