package by.sacuta.ExchangeService.model.dto;

import by.sacuta.ExchangeService.model.enums.CourseStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class CourseDTO {

    private Long id;
    private String name;
    private LocalDate startCourse;
    private LocalDate endCourse;
    private Integer days;
    private SectionDTO section;
    private ProfileDTO speaker;
    private CourseStatus courseStatus;
    private List<LessonDTO> lessons ;
    private List<CommentDTO> comments;
    private List<ProfileDTO> listeners ;
    private Integer price;

    @Override
    public String toString() {
        return
                 name ;
    }
}
