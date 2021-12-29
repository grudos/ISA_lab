package application.athlete.entity;

import application.discipline.entity.Discipline;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private int birthYear;

    @ManyToOne
    @JoinColumn(name = "discipline")
    private Discipline discipline;

}
