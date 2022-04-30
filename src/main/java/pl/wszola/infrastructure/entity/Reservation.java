package pl.wszola.infrastructure.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String domainId;
    @OneToOne
    private RentItem rentItem;
    private LocalDate rentPeriodStart;
    private LocalDate rentPeriodFinish;
    @OneToOne
    private Person renter;

    public Reservation(String domainId,RentItem rentItem, LocalDate rentPeriodStart,
                       LocalDate rentPeriodFinish, Person renter) {
        this.domainId = domainId;
        this.rentItem = rentItem;
        this.rentPeriodStart = rentPeriodStart;
        this.rentPeriodFinish = rentPeriodFinish;
        this.renter = renter;
    }

}
