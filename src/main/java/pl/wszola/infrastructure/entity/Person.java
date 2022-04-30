package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String surname;
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    public Person(String firstname, String surname, PersonType personType) {
        this.firstname = firstname;
        this.surname = surname;
        this.personType = personType;
    }
}
