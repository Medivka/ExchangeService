package by.sacuta.ExchangeService.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@Table(name = "section")
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "section", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Course> courseList = new LinkedList<>();

    public Section(String name) {
        this.name = name;
    }
}
