package application.service;

import application.entity.Discipline;
import application.event.repository.DisciplineEventRepository;
import application.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    private final DisciplineRepository repository;
    //private final DisciplineEventRepository eventRepository;

    @Autowired
    public DisciplineService(DisciplineRepository repository/*, DisciplineEventRepository eventRepository*/) {
        this.repository = repository;
        //this.eventRepository = eventRepository;
    }

    public Optional<Discipline> find(String name) {
        return repository.findByName(name);
    }

    public List<Discipline> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Discipline create(Discipline discipline) {
        //eventRepository.create(discipline);
        return repository.save(discipline);
    }

    @Transactional
    public void update(Discipline discipline) {
        repository.save(discipline);
    }

    @Transactional
    public void delete(String name) {
        //Optional<Discipline> discipline = find(name);
        repository.deleteById(name);
        //eventRepository.delete(discipline.get());
    }

}
