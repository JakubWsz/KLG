package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String domainId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private RentItem rentItem;
    private LocalDate rentPeriodStart;
    private LocalDate rentPeriodFinish;
    @OneToOne(fetch = FetchType.LAZY)
    private Person renter;

    public Reservation(String domainId, RentItem rentItem, LocalDate rentPeriodStart,
                       LocalDate rentPeriodFinish, Person renter) {
        this.domainId = domainId;
        this.rentItem = rentItem;
        this.rentPeriodStart = rentPeriodStart;
        this.rentPeriodFinish = rentPeriodFinish;
        this.renter = renter;
    }
}
