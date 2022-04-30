package pl.wszola.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ReservationRequest {
    private final RentItem rentItem;
    private final LocalDate RentPeriodStart;
    private final LocalDate RentPeriodFinish;
    private final Person renter;
}
