package application.athlete.dto;

import application.athlete.entity.Athlete;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAthleteRequest {

    private String name;
    private int birthYear;

    public static BiFunction<Athlete, UpdateAthleteRequest, Athlete> dtoToEntityUpdater() {
        return (athlete, request) -> {
            athlete.setName(request.getName());
            athlete.setBirthYear(request.getBirthYear());
            return athlete;
        };
    }
}

