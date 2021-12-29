package application.discipline.service;

import application.discipline.entity.Discipline;
import application.discipline.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DisciplineService {

    private final DisciplineRepository repository;

    @Autowired
    public DisciplineService(DisciplineRepository repository) {
        this.repository = repository;
    }

    public Optional<Discipline> find(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public Discipline create(Discipline discipline) {
        return repository.save(discipline);
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }

}
