package application.athlete.service;

import application.athlete.entity.Athlete;
import application.athlete.repository.AthleteRepository;
import application.discipline.entity.Discipline;
import application.discipline.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    private AthleteRepository athleteRepository;
    private DisciplineRepository disciplineRepository;

    @Autowired
    public AthleteService(AthleteRepository athleteRepository, DisciplineRepository disciplineRepository) {
        this.athleteRepository = athleteRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public Optional<Athlete> find(String name, Long id) {
        Optional<Discipline> discipline = disciplineRepository.findByName(name);
        if (discipline.isPresent()) {
            return athleteRepository.findByIdAndDiscipline(id, discipline.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Athlete> findAll(Discipline discipline) {
        return athleteRepository.findAllByDiscipline(discipline);
    }

    @Transactional
    public Athlete create(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    @Transactional
    public void update(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    @Transactional
    public void delete(Long Id) {
        athleteRepository.deleteById(Id);
    }

}
