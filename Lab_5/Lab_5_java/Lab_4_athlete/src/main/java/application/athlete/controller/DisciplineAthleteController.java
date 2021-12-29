package application.athlete.controller;

import application.athlete.dto.CreateAthleteRequest;
import application.athlete.dto.GetAthleteResponse;
import application.athlete.dto.GetAthletesResponse;
import application.athlete.dto.UpdateAthleteRequest;
import application.athlete.entity.Athlete;
import application.athlete.service.AthleteService;
import application.discipline.entity.Discipline;
import application.discipline.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/disciplines/{name}/athletes")
public class DisciplineAthleteController {

    private final AthleteService athleteService;
    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineAthleteController(AthleteService athleteService, DisciplineService disciplineService) {
        this.athleteService = athleteService;
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public ResponseEntity<GetAthletesResponse> getAthletes(@PathVariable("name") String name) {
        Optional<Discipline> discipline = disciplineService.find(name);
        return discipline.map(value -> ResponseEntity.ok(GetAthletesResponse.entityToDtoMapper().apply(athleteService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAthleteResponse> getAthlete(@PathVariable("name") String name, @PathVariable("id") long id) {
        return athleteService.find(name, id)
                .map(value -> ResponseEntity.ok(GetAthleteResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createAthlete(@PathVariable("name") String name, @RequestBody CreateAthleteRequest request, UriComponentsBuilder builder) {
        Optional<Discipline> discipline = disciplineService.find(name);
        if (discipline.isPresent()) {
            Athlete athlete = CreateAthleteRequest
                    .dtoToEntityMapper(newName -> disciplineService.find(newName).orElseThrow())
                    .apply(request);
            athlete = athleteService.create(athlete);
            return ResponseEntity.created(builder.pathSegment("api", "disciplines", "{name}", "athletes", "{id}")
                    .buildAndExpand(discipline.get().getName(), athlete.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable("name") String name, @PathVariable("id") long id) {
        Optional<Athlete> athlete = athleteService.find(name, id);
        if (athlete.isPresent()) {
            athleteService.delete(athlete.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAthlete(@PathVariable("name") String name, @RequestBody UpdateAthleteRequest request, @PathVariable("id") long id) {
        Optional<Athlete> athlete = athleteService.find(name, id);
        if (athlete.isPresent()) {
            UpdateAthleteRequest.dtoToEntityUpdater().apply(athlete.get(), request);
            athleteService.update(athlete.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

