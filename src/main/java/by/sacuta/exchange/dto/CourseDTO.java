package by.sacuta.exchange.dto;

import by.sacuta.exchange.domain.enums.CourseStatus;
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
