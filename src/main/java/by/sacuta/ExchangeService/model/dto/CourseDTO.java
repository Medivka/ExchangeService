package by.sacuta.ExchangeService.model.dto;

import by.sacuta.ExchangeService.model.enums.CourseStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Data
public class CourseDTO {

    private Long id;
    private String name;
    private LocalDate startCourse;
    private LocalDate endCourse;
    private Integer days;
    private ProfileDTO speaker;
    private CourseStatus courseStatus;
    private List<LessonDTO> lessons = new LinkedList<>();
    private List<CommentDTO> comments = new LinkedList<>();
    private List<ProfileDTO> listeners = new LinkedList<>();
    private Integer price;

}
