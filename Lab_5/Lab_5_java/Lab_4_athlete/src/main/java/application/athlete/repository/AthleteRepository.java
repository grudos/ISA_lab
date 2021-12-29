package application.athlete.repository;

import application.athlete.entity.Athlete;
import application.discipline.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Optional<Athlete> findById(Long id);

    Optional<Athlete> findByIdAndDiscipline(Long id, Discipline discipline);

    List<Athlete> findAll();

    List<Athlete> findAllByDiscipline(Discipline discipline);

}
