package application.discipline.repository;

import application.discipline.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {

    Optional<Discipline> findByName(String name);

}
