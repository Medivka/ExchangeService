package by.sacuta.exchange.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile")
    private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY
//            , cascade = CascadeType.MERGE
    )
    @JoinTable(name = "course_comment",
            joinColumns = @JoinColumn(name = "id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<Course> courses = new LinkedList<>();

    public Comment(String message, Profile profile) {
        this.message = message;
        this.profile=profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return " " + message;
    }
}
