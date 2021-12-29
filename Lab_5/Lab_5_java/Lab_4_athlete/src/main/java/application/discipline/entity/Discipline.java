package application.discipline.entity;

import application.athlete.entity.Athlete;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "disciplines")
public class Discipline {

    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.REMOVE)
    private List<Athlete> athletes;

}
