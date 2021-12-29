package application.configuration;

import application.entity.Discipline;
import application.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final DisciplineService disciplineService;

    @Autowired
    public InitializedData(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @PostConstruct
    private synchronized void init() {
        Discipline skokiNarciarskie = Discipline.builder()
                .name("Skoki narciarskie")
                .typeOfSport("Sport indywidualny")
                .numberOfFans(Long.valueOf(2000000))
                .build();

        Discipline pilkaNozna = Discipline.builder()
                .name("Pilka nozna")
                .typeOfSport("Gra zespolowa")
                .numberOfFans(Long.valueOf(2000000000))
                .build();

        Discipline siatkowka = Discipline.builder()
                .name("Siatkowka")
                .typeOfSport("Gra zespolowa")
                .numberOfFans(Long.valueOf(800000000))
                .build();

        disciplineService.create(skokiNarciarskie);
        disciplineService.create(pilkaNozna);
        disciplineService.create(siatkowka);
    }

}
