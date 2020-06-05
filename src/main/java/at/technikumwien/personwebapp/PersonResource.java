package at.technikumwien.personwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// see http://localhost:8081/resources/persons
// see http://localhost:8081/resources/persons/1

@RestController // dadurch bekommen wir json zurück
@RequestMapping("resources/persons")
public class PersonResource {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person retrieve(@PathVariable long id){
        return personRepository.findById(id).get();
    }

    @GetMapping
    public List<Person> retrieveAll(@RequestParam(name = "all", defaultValue = "false") boolean all) {
        if(all){
            return personRepository.findAll();
        }
        return personRepository.findAllByActiveTrue();
    }
}
