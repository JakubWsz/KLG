package pl.wszola.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.wszola.api.response.ReservationView;
import pl.wszola.infrastructure.entity.Reservation;

public class ReservationToReservationView implements Converter<Reservation, ReservationView> {
    @Override
    public ReservationView convert(Reservation reservation) {
        return new ReservationView(
                reservation.getDomainId(),
                reservation.getRentItem(),
                reservation.getRentPeriodStart(),
                reservation.getRentPeriodFinish(),
                reservation.getLessor(),
                reservation.getRenter()
        );
    }
}
