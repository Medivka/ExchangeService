package by.sacuta.exchange.domain.model;

import by.sacuta.exchange.domain.enums.CourseStatus;
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
    @JoinColumn(name = "section")
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker")
    private Profile speaker;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @OneToMany(mappedBy = "course", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lesson> lessons = new LinkedList<>();

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "course_comment",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_comment"))
    private List<Comment> comments = new LinkedList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(name = "course_profile",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_profile"))
    private List<Profile> listeners = new LinkedList<>();

    private Integer price;


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

    public LocalDate getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(LocalDate startCourse) {
        this.startCourse = startCourse;
    }

    public LocalDate getEndCourse() {
        return endCourse;
    }

    public void setEndCourse(LocalDate endCourse) {
        this.endCourse = endCourse;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Profile getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Profile speaker) {
        this.speaker = speaker;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Profile> getListeners() {
        return listeners;
    }

    public void setListeners(List<Profile> listeners) {
        this.listeners = listeners;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
