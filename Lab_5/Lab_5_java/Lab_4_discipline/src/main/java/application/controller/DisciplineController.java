package application.controller;

import application.dto.CreateDisciplineRequest;
import application.dto.GetDisciplineResponse;
import application.dto.GetDisciplinesResponse;
import application.dto.UpdateDisciplineRequest;
import application.entity.Discipline;
import application.event.repository.DisciplineEventRepository;
import application.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;
    private final DisciplineEventRepository eventRepository;

    @Autowired
    public DisciplineController(DisciplineService disciplineService, DisciplineEventRepository eventRepository) {
        this.disciplineService = disciplineService;
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public ResponseEntity<GetDisciplinesResponse> getDisciplines() {
        List<Discipline> all = disciplineService.findAll();
        Function<Collection<Discipline>, GetDisciplinesResponse> mapper = GetDisciplinesResponse.entityToDtoMapper();
        GetDisciplinesResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetDisciplineResponse> getDiscipline(@PathVariable("name") String name) {
        return disciplineService.find(name)
                .map(value -> ResponseEntity.ok(GetDisciplineResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createDiscipline(@RequestBody CreateDisciplineRequest request, UriComponentsBuilder builder) {
        Discipline discipline = CreateDisciplineRequest.dtoToEntityMapper().apply(request);
        discipline = disciplineService.create(discipline);
        eventRepository.create(discipline);
        return ResponseEntity.created(builder.pathSegment("api", "disciplines", "{name}")
                .buildAndExpand(discipline.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable("name") String name) {
        Optional<Discipline> discipline = disciplineService.find(name);
        if (discipline.isPresent()) {
            eventRepository.delete(discipline.get());
            disciplineService.delete(discipline.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateDiscipline(@RequestBody UpdateDisciplineRequest request, @PathVariable("name") String name) {
        Optional<Discipline> discipline = disciplineService.find(name);
        if (discipline.isPresent()) {
            UpdateDisciplineRequest.dtoToEntityUpdater().apply(discipline.get(), request);
            disciplineService.update(discipline.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

