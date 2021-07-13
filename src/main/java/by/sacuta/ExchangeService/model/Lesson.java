package by.sacuta.ExchangeService.model;

import by.sacuta.ExchangeService.model.enums.LessonStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {
//            CascadeType.MERGE,
//            CascadeType.REFRESH
    }
    )
    @JoinTable(name = "course_lesson",
            joinColumns = @JoinColumn(name = "id_lesson"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<Course> courses = new LinkedList<>();

    public Lesson(String name, LocalDateTime localDateTime, Integer duration, LessonStatus lessonStatus, Integer price) {
        this.name = name;
        this.localDateTime = localDateTime;
        this.duration = duration;
        this.lessonStatus = lessonStatus;
        this.price = price;
    }
}
