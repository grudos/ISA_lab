package application.repository;

import application.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {

    Optional<Discipline> findByName(String name);

    List<Discipline> findAll();

}
