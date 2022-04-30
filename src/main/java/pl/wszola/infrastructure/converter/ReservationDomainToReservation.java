package pl.wszola.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.infrastructure.entity.Reservation;

public class ReservationDomainToReservation implements Converter<ReservationDomain,Reservation> {
    @Override
    public Reservation convert(ReservationDomain reservationDomain) {
        return new Reservation(
                reservationDomain.getDomainId(),
                reservationDomain.getRentItem(),
                reservationDomain.getRentPeriodStart(),
                reservationDomain.getRentPeriodFinish(),
                reservationDomain.getRenter()
        );
    }
}
