package by.sacuta.ExchangeService.dto;

import by.sacuta.ExchangeService.model.Role;
import by.sacuta.ExchangeService.model.enums.ProfileStatus;
import lombok.Data;

import java.util.List;

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
