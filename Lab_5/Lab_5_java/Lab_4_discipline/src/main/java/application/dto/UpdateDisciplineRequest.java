package application.dto;

import application.entity.Discipline;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateDisciplineRequest {

    private String name;
    private String typeOfSport;
    private Long numberOfFans;

    public static BiFunction<Discipline, UpdateDisciplineRequest, Discipline> dtoToEntityUpdater() {
        return (discipline, request) -> {
            discipline.setTypeOfSport(request.getTypeOfSport());
            discipline.setNumberOfFans(request.getNumberOfFans());
            return discipline;
        };
    }
}