package application.athlete.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAthletesResponse {

    @Singular
    private List<Athlete> athletes;

    public static Function<Collection<application.athlete.entity.Athlete>, GetAthletesResponse> entityToDtoMapper() {
        return athletes -> {
            GetAthletesResponseBuilder response = GetAthletesResponse.builder();
            athletes.stream()
                    .map(athlete -> Athlete.builder()
                            .Id(athlete.getId())
                            .name(athlete.getName())
                            .birthYear(athlete.getBirthYear())
                            .build())
                    .forEach(response::athlete);
            return response.build();
        };
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Athlete {

        private Long Id;
        private String name;
        private int birthYear;

    }

}
