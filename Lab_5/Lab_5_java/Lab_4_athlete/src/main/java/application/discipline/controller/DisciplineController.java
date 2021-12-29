package application.discipline.controller;

import application.discipline.dto.CreateDisciplineRequest;
import application.discipline.entity.Discipline;
import application.discipline.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @PostMapping
    public ResponseEntity<Void> createDiscipline(@RequestBody CreateDisciplineRequest request, UriComponentsBuilder builder) {
        Discipline discipline = CreateDisciplineRequest.dtoToEntityMapper().apply(request);
        discipline = disciplineService.create(discipline);
        return ResponseEntity.created(builder.pathSegment("api", "disciplines", "{name}")
                .buildAndExpand(discipline.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable("name") String name) {
        Optional<Discipline> discipline = disciplineService.find(name);
        if (discipline.isPresent()) {
            disciplineService.delete(discipline.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

