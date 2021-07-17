package by.sacuta.ExchangeService.model.model;

import by.sacuta.ExchangeService.model.enums.LessonStatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "lesson")
@NoArgsConstructor
@AllArgsConstructor

public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime localDateTime;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private LessonStatus lessonStatus;
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    public Lesson(String name,Course course, LocalDateTime localDateTime, Integer duration, LessonStatus lessonStatus, Integer price) {
        this.name = name;
        this.course=course;
        this.localDateTime = localDateTime;
        this.duration = duration;
        this.lessonStatus = lessonStatus;
        this.price = price;
    }
}
