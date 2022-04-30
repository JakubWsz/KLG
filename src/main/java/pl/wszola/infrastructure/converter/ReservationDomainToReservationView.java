package pl.wszola.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.reservation.model.ReservationDomain;

public class ReservationDomainToReservationView implements Converter<ReservationDomain, ReservationView> {
    @Override
    public ReservationView convert(ReservationDomain reservation) {
        return new ReservationView(
                reservation.getDomainId(),
                reservation.getRentItem(),
                reservation.getRentPeriodStart(),
                reservation.getRentPeriodFinish(),
                reservation.getRenter()
        );
    }
}
