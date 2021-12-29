package application.athlete.dto;

import application.athlete.entity.Athlete;
import application.discipline.entity.Discipline;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateAthleteRequest {

    private Long Id;
    private String name;
    private int birthYear;
    private String discipline;

    public static Function<CreateAthleteRequest, Athlete> dtoToEntityMapper(Function<String, Discipline> disciplineFunction) {
        return request -> Athlete.builder()
                .id(request.getId())
                .name(request.getName())
                .birthYear(request.getBirthYear())
                .discipline(disciplineFunction.apply(request.getDiscipline()))
                .build();
    }

}

