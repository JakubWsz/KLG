package pl.wszola.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UpdateReservationRequest {
    private RentItem rentItem;
    private LocalDate RentPeriodStart;
    private LocalDate RentPeriodFinish;
    private Person renter;
}
