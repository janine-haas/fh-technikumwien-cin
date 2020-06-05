package at.technikumwien.personwebapp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_person") // der Fehler ist hier ok
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private boolean active;

    @JsonIgnore
    public String getName(){
        if(firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("first name is  null or empty");
        }
        if(lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("last name is  null or empty");
        }
        return firstName + " " + lastName;
    }
}
