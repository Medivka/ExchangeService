package by.sacuta.ExchangeService.model.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private String message;
    private ProfileDTO profile;

}
