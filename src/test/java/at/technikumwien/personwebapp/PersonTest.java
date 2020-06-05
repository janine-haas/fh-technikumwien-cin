package at.technikumwien.personwebapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp(){
        person = new Person(
                1L,
                Sex.MALE,
                "Markus",
                "Mustermann",
                LocalDate.of(1990, 1, 1),
                false
        );
    }

    @Test
    public void testGetName(){
        assertEquals("Markus Mustermann", person.getName());
    }

    @Test
    public void testGetNameWithFirstNameNull(){
        person.setFirstName(null);
        assertThrows( IllegalArgumentException.class,
                () -> person.getName());
    }

    @Test
    public void testGetNameWirthFirstNameEmpty(){
        person.setFirstName(" ");
        assertThrows( IllegalArgumentException.class,
                () -> person.getName());
    }

    // TODO add more tests
}
