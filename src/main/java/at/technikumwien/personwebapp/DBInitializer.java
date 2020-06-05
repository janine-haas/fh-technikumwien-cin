package at.technikumwien.personwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;

@Configuration
// nur die Klassen, die @Configuration haben, werden von Spring gefunden
@Profile("default") // wenn man kein Profil setzt, dann wird das default genommen
public class DBInitializer {

    @Autowired
    private PersonRepository personRepository;
    // Dependency Injection mit @Autowired, es wird automatisch mit einer lauffähigen Instanz verknüpft

    // Klassen- und Methodenname sind egal wie die heißen
    // der Event Listener wird aufgerufen, wenn die Applikation fertig geladen ist und Request empfangen kann
    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationEvent(){
        personRepository.saveAll(
                List.of(
                        new Person(
                                null,
                                Sex.MALE,
                                "Markus",
                                "Mustermann",
                                LocalDate.of(1990, 1, 1),
                                true
                        ),
                        new Person(
                                null,
                                Sex.FEMALE,
                                "Martina",
                                "Musterfrau",
                                LocalDate.of(1990, 2, 1),
                                true
                        )
                )
        );
    }
}
