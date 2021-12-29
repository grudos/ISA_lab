package application.entity;

import lombok.*;

import javax.persistence.*;

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

    private String typeOfSport;

    private Long numberOfFans;

}
