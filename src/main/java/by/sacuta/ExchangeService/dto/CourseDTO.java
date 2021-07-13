package by.sacuta.ExchangeService.dto;

import by.sacuta.ExchangeService.model.Comment;
import by.sacuta.ExchangeService.model.Lesson;
import by.sacuta.ExchangeService.model.Profile;
import by.sacuta.ExchangeService.model.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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
