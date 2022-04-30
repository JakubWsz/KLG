package pl.wszola.domain.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationDomain {
    private String domainId;
    private RentItem rentItem;
    private LocalDate rentPeriodStart;
    private LocalDate rentPeriodFinish;
    private Person lessor;
    private Person renter;

}
