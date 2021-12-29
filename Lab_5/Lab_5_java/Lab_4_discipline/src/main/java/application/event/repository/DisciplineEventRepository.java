package application.event.repository;


import application.entity.Discipline;
import application.event.dto.CreateDisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class DisciplineEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public DisciplineEventRepository(@Value("${athletes.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Discipline discipline) {
        restTemplate.delete("/disciplines/{name}", discipline.getName());
    }

    public void create(Discipline discipline) {
        restTemplate.postForLocation("/disciplines", CreateDisciplineRequest.entityToDtoMapper().apply(discipline));
    }

}
