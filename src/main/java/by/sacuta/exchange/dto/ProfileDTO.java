package by.sacuta.exchange.dto;

import by.sacuta.exchange.domain.enums.ProfileStatus;
import by.sacuta.exchange.domain.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class ProfileDTO {

    private long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Integer age;
    private String email;
    private String city;
    private ProfileStatus status;
  
    @Override
    public String toString() {
        return name +
                " " + lastname ;
    }
}
