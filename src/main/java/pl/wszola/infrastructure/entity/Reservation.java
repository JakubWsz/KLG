package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private RentItem rentItem;
    private LocalDate rentPeriodStart;
    private LocalDate rentPeriodFinish;
    @OneToOne
    private Person lessor;
    @OneToOne
    private Person renter;

    public Reservation(RentItem rentItem, LocalDate rentPeriodStart, LocalDate rentPeriodFinish,
                       Person lessor, Person renter) {
        this.rentItem = rentItem;
        this.rentPeriodStart = rentPeriodStart;
        this.rentPeriodFinish = rentPeriodFinish;
        this.lessor = lessor;
        this.renter = renter;
    }
}
