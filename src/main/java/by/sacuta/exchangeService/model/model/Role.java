package by.sacuta.exchangeService.model.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    Set<Profile> profiles;

    @Override
    public String getAuthority() {
        return getRole();
    }

}

