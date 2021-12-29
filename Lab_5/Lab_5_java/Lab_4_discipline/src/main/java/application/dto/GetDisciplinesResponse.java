package application.dto;

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
public class GetDisciplinesResponse {

    @Singular
    private List<Discipline> disciplines;

    public static Function<Collection<application.entity.Discipline>, GetDisciplinesResponse> entityToDtoMapper() {
        return disciplines -> {
            GetDisciplinesResponseBuilder response = GetDisciplinesResponse.builder();
            disciplines.stream()
                    .map(discipline -> Discipline.builder()
                            .name(discipline.getName())
                            .typeOfSport(discipline.getTypeOfSport())
                            .numberOfFans(discipline.getNumberOfFans())
                            .build())
                    .forEach(response::discipline);
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
    public static class Discipline {

        private String name;
        private String typeOfSport;
        private Long numberOfFans;

    }

}

