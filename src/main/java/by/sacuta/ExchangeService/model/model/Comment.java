package by.sacuta.ExchangeService.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile")
    private Profile profile;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "course_comment",
            joinColumns = @JoinColumn(name = "id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<Course> courses = new LinkedList<>();
    public Comment(String message, Profile profile) {
        this.message = message;
        this.profile=profile;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return " " + message;
    }
}
