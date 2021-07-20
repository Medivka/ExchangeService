package by.sacuta.exchangeService.model.model;

import by.sacuta.exchangeService.model.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public  class Profile implements UserDetails {

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
    @ManyToMany(fetch = FetchType.EAGER)
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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}