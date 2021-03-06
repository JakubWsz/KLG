package pl.wszola.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ReservationView {
    private final String domainId;
    private final RentItem rentItem;
    private final LocalDate rentPeriodStart;
    private final LocalDate rentPeriodFinish;
    private final Person renter;

}
