package by.sacuta.ExchangeService.model.model;


import by.sacuta.ExchangeService.model.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public  class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="username")
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Integer age;
    private String email;
    private String city;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @ManyToMany
    @JoinTable(name = "profile_role",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;


    @OneToMany(mappedBy = "speaker", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Course> courseList = new LinkedList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    private List<Comment> comments = new LinkedList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY
            //            , cascade = CascadeType.MERGE
    )
    @JoinTable(name = "course_profile",
            joinColumns = @JoinColumn(name = "id_profile"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<Course> courses = new LinkedList<>();

    public Profile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.city = city;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                ", courseList=" + courseList +
                ", comments=" + comments +
                ", courses=" + courses +
                '}';
    }
}