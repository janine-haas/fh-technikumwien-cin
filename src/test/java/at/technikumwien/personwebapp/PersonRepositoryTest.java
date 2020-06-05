package at.technikumwien.personwebapp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // hier wird tatsächlich der Tomcat gestartet und eine Verbindung zur DB hergestellt, DB Schicht wird automatisch hochgefahren
// bei anderen Technologien ist das wesentlich komplizierter
@ActiveProfiles("test") // das verursacht, dass der TestDBInitializer ausgeführt wird und nicht der DBInitializer
@Transactional // automatisch am Ende einer Methode wird die Transaktion(save) rückgängig gemacht, damit man immer den gleich Datenbestand
// gilt für alle Methoden, kann man aber auch nur über eine Methode schreiben
@Tag("integration-test") // Name kann frei wählen, muss dann nur der gleich sein
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave(){
        var person = new Person(
                null,
                Sex.MALE,
                "Maxi",
                "Musterkind",
                LocalDate.of(2010, 1, 1),
                true);
        personRepository.save(person);

        assertNotNull(person.getId());
        assertEquals(3, personRepository.count());
    }

    // das hier ist ein Integrationtest, man muss aufpassen, dass die Datenbank entsprechend hergerichtet ist
    @Test
    public void testFindAllByActiveTrue(){
        var persons = personRepository.findAllByActiveTrue();

        assertEquals(1, persons.size());
    }

    // TODO add more tests here

}
