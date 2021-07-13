package by.sacuta.ExchangeService.model.model;

import by.sacuta.ExchangeService.model.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate startCourse;
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate endCourse;
        private Integer days;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "speaker")
        private Role.Profile speaker;

        @Enumerated(EnumType.STRING)
        private CourseStatus courseStatus;
        @ManyToMany(fetch = FetchType.LAZY
                , cascade = CascadeType.MERGE
        )
        @JoinTable(name = "course_lesson",
                joinColumns = @JoinColumn(name = "id_course"),
                inverseJoinColumns = @JoinColumn(name = "id_lesson"))
        private List<Lesson> lessons = new LinkedList<>();
        @ManyToMany(fetch = FetchType.LAZY
                , cascade = {
//           CascadeType.MERGE,
        })
        @JoinTable(name = "course_comment",
                joinColumns = @JoinColumn(name = "id_course"),
                inverseJoinColumns = @JoinColumn(name = "id_comment"))
        private List<Comment> comments = new LinkedList<>();
        @JsonIgnore
        @ManyToMany(fetch = FetchType.LAZY
//            , cascade = CascadeType.MERGE
        )
        @JoinTable(name = "course_profile",
                joinColumns = @JoinColumn(name = "id_course"),
                inverseJoinColumns = @JoinColumn(name = "id_profile"))
        private List<Role.Profile> listeners = new LinkedList<>();
        private Integer price;

    }