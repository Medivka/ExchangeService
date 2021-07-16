package by.sacuta.ExchangeService.model.dto;

import by.sacuta.ExchangeService.model.enums.ProfileStatus;
import lombok.Data;

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
}
