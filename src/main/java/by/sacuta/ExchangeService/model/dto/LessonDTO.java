package by.sacuta.ExchangeService.model.dto;

import by.sacuta.ExchangeService.model.enums.LessonStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LessonDTO {

    private Long id;
    private String name;
    private LocalDateTime localDateTime;
    private Integer duration;
    private LessonStatus lessonStatus;
    private Integer price;

}
