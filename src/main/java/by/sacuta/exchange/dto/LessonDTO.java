package by.sacuta.exchange.dto;

import by.sacuta.exchange.domain.enums.LessonStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonDTO {

    private Long id;
    private String name;
    private CourseDTO course;
    private LocalDateTime localDateTime;
    private Integer duration;
    private LessonStatus lessonStatus;
    private Integer price;

}
