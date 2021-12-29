package application.event.dto;

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

    public static Function<Discipline, CreateDisciplineRequest> entityToDtoMapper() {
        return entity -> CreateDisciplineRequest.builder()
                .name(entity.getName())
                .build();
    }

}