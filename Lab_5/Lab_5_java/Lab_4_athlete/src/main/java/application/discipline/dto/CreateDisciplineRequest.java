package application.discipline.dto;

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
public class CreateDisciplineRequest {

    private String name;

    public static Function<CreateDisciplineRequest, Discipline> dtoToEntityMapper() {
        return request -> Discipline.builder()
                .name(request.getName())
                .build();
    }

}
