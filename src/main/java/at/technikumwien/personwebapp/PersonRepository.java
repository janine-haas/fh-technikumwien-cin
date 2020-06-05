package at.technikumwien.personwebapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // hier müssen nichts mehr machen, nur von JpaRepository erben
    // dann bekommen wir schon alle CRUD Methoden
    // JpaRepository ist von Spring Data

    // Möchte man spezielle Methoden haben, dann ich die hier definieren
    // diese müssen aber einer Namenskonvention folgen
    List<Person> findAllByActiveTrue();
    //List<Person> findAllByLastNameAAndFirstNameOrderByLastNameAsc(String ln, String fn);
}
