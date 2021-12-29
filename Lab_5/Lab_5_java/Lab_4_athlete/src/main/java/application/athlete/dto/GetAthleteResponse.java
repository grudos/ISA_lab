package application.athlete.dto;

import application.athlete.entity.Athlete;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAthleteResponse {

    private Long Id;
    private String name;
    private int birthYear;
    private String discipline;

    public static Function<Athlete, GetAthleteResponse> entityToDtoMapper() {
        return athlete -> GetAthleteResponse.builder()
                .Id(athlete.getId())
                .name(athlete.getName())
                .birthYear(athlete.getBirthYear())
                .discipline(athlete.getDiscipline().getName())
                .build();
    }

}

