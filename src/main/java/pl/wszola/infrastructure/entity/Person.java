package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
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
