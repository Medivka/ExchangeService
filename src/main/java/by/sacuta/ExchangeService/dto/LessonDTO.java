package by.sacuta.ExchangeService.dto;

import by.sacuta.ExchangeService.model.enums.LessonStatus;
import java.time.LocalDateTime;


public class LessonDTO {

    private Long id;
    private String name;
    private LocalDateTime localDateTime;
    private Integer duration;
    private LessonStatus lessonStatus;
    private Integer price;

}
