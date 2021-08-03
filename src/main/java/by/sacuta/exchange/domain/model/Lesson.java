package by.sacuta.exchange.domain.model;

import by.sacuta.exchange.domain.enums.LessonStatus;


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
        this.localDateTime = localDateTime;
        this.duration = duration;
        this.lessonStatus = lessonStatus;
        this.price = price;
        this.course=course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LessonStatus getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(LessonStatus lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
