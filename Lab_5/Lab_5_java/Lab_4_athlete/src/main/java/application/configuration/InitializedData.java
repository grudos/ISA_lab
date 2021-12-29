package application.configuration;

import application.athlete.entity.Athlete;
import application.athlete.service.AthleteService;
import application.discipline.entity.Discipline;
import application.discipline.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final AthleteService athleteService;
    private final DisciplineService disciplineService;

    @Autowired
    public InitializedData(AthleteService athleteService, DisciplineService disciplineService) {
        this.athleteService = athleteService;
        this.disciplineService = disciplineService;
    }

    @PostConstruct
    private synchronized void init() {

        Discipline skokiNarciarskie = Discipline.builder()
                .name("Skoki narciarskie")
                .build();

        Discipline pilkaNozna = Discipline.builder()
                .name("Pilka nozna")
                .build();

        Discipline siatkowka = Discipline.builder()
                .name("Siatkowka")
                .build();

        disciplineService.create(skokiNarciarskie);
        disciplineService.create(pilkaNozna);
        disciplineService.create(siatkowka);

        Athlete adam = Athlete.builder()
                .id(Long.valueOf(1))
                .name("Adam Malysz")
                .birthYear(1977)
                .discipline(skokiNarciarskie)
                .build();

        Athlete robert = Athlete.builder()
                .id(Long.valueOf(2))
                .name("Robert Lewandowski")
                .birthYear(1988)
                .discipline(pilkaNozna)
                .build();

        Athlete bartosz = Athlete.builder()
                .id(Long.valueOf(3))
                .name("Bartosz Kurek")
                .birthYear(1988)
                .discipline(siatkowka)
                .build();

        athleteService.create(adam);
        athleteService.create(robert);
        athleteService.create(bartosz);
    }

}
