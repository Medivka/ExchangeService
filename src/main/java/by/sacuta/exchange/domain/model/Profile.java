package by.sacuta.exchange.domain.model;

import by.sacuta.exchange.domain.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    @NotEmpty(message = "not be empty")
    @Column(name="username")
    @Size(min = 2,max = 15,message = "invalid username")
    @NotEmpty(message = "not be empty")
    private String username;
    @NotEmpty(message = "not be empty")
    @Size(min = 2,max = 15,message = "invalid password")
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
    private Set<Role> roles;
    @OneToMany(mappedBy = "speaker", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Course> courseList = new LinkedList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY
         , cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE
    }
    )
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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