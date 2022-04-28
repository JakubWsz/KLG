package pl.wszola.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.wszola.domain.reservation.ReservationDomain;
import pl.wszola.infrastructure.entity.Reservation;

public class ReservationToReservationDomain implements Converter<Reservation, ReservationDomain>{
    @Override
    public ReservationDomain convert(Reservation reservation) {
        return new ReservationDomain(
                reservation.getDomainId(),
                reservation.getRentItem(),
                reservation.getRentPeriodStart(),
                reservation.getRentPeriodFinish(),
                reservation.getLessor(),
                reservation.getRenter()
        );
    }
}
