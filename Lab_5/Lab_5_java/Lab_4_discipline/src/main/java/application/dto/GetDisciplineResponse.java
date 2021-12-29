package application.dto;

import application.entity.Discipline;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDisciplineResponse {

    private String name;
    private String typeOfSport;
    private Long numberOfFans;

    public static Function<Discipline, GetDisciplineResponse> entityToDtoMapper() {
        return discipline -> GetDisciplineResponse.builder()
                .name(discipline.getName())
                .typeOfSport(discipline.getTypeOfSport())
                .numberOfFans(discipline.getNumberOfFans())
                .build();
    }

}
