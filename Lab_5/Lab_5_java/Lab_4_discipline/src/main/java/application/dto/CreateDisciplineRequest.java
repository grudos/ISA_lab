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
public class CreateDisciplineRequest {

    private String name;
    private String typeOfSport;
    private Long numberOfFans;

    public static Function<CreateDisciplineRequest, Discipline> dtoToEntityMapper() {
        return request -> Discipline.builder()
                .name(request.getName())
                .typeOfSport(request.getTypeOfSport())
                .numberOfFans(request.getNumberOfFans())
                .build();
    }

}
