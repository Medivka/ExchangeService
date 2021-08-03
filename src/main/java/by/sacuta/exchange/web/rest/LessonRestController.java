package by.sacuta.exchange.web.rest;

import by.sacuta.exchange.domain.enums.LessonStatus;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.dto.LessonDTO;
import by.sacuta.exchange.service.LessonService;
import by.sacuta.exchange.service.MyModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/lesson")
public class LessonRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonRestController.class);

    private final MyModelMapper myModelMapper;
    private final LessonService lessonService;

    public LessonRestController(MyModelMapper myModelMapper, LessonService lessonService) {
        this.myModelMapper = myModelMapper;
        this.lessonService = lessonService;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<LessonDTO>> getAll() {
        LOGGER.info("rest/lesson/get/all ");

        final List<LessonDTO> lessonDTOList = new LinkedList<>();
        for (Lesson l : lessonService.getAll()
        ) {
            lessonDTOList.add(myModelMapper.mapToLessonDTO(l));
        }
        return lessonDTOList != null && !lessonDTOList.isEmpty()
                ? new ResponseEntity<>(lessonDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<LessonDTO> getById(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/lesson/get/{%s} ",id));

        final LessonDTO lessonDTO = myModelMapper.mapToLessonDTO(lessonService.findById(id));
        return lessonDTO != null
                ? new ResponseEntity<>(lessonDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/lesson/delete/{%s} ",id));

        boolean delete = false;
        final List<Lesson> lessons = lessonService.getAll();
        for (Lesson l : lessons) {
            if (l.getId().equals(id)) {
                delete = true;
                lessonService.delete(id);
            }
        }
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody LessonDTO lessonDTO) {
        LOGGER.info(String.format( "rest/lesson/save/{%s} ",lessonDTO.getName()));
        lessonService.save(myModelMapper.mapToLesson(lessonDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody LessonDTO lessonDTO) {
        LOGGER.info(String.format( "rest/lesson/update/{%s} ",id));
        boolean update = false;
        final List<Lesson> lessons = lessonService.getAll();
        for (Lesson l : lessons) {
            if (l.getId().equals(id)) {
                l = myModelMapper.mapToLesson(lessonDTO);
                update = true;
                lessonService.update(l);
            }
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/getLessonAfterDate/{date}")
    public ResponseEntity<List<LessonDTO>> afterDate(@PathVariable(name = "date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        LOGGER.info(String.format( "rest/lesson/getLessonAfterDate/{%s} ",date));
        final List<LessonDTO> lessons = new LinkedList<>();
        for (Lesson l : lessonService.findByDate(date)
        ) {
            lessons.add(myModelMapper.mapToLessonDTO(l));
        }
        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<List<LessonDTO>> findByName(@PathVariable(name = "name") String name) {
        LOGGER.info(String.format( "rest/lesson/findByName/{%s} ",name));
        final List<LessonDTO> lessons = new LinkedList<>();
        for (Lesson l : lessonService.findByName(name)
        ) {
            lessons.add(myModelMapper.mapToLessonDTO(l));
        }
        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findByStatus/{status}")
    public ResponseEntity<List<LessonDTO>> findByStatus(@PathVariable(name = "status") int status) {
        LOGGER.info(String.format( "rest/lesson/findByStatus/{%s} ",status));

        final List<LessonDTO> lessons = new LinkedList<>();
        LessonStatus lessonStatus = LessonStatus.GROUP;
        switch (status) {
            case 1:
                lessonStatus = LessonStatus.GROUP;
                break;
            case 2:
                lessonStatus = LessonStatus.INDIVIDUAL;
                break;
        }
        for (Lesson l : lessonService.findByStatus(lessonStatus)
        ) {
            lessons.add(myModelMapper.mapToLessonDTO(l));
        }
        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
