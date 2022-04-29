package pl.wszola.infrastructure.converter;

import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.reservation.ReservationDomain;
import pl.wszola.infrastructure.entity.Reservation;

public class Converter {

    public static ReservationView convertReservationToReservationView(Reservation reservation) {
        return new ReservationView(
                reservation.getDomainId(),
                reservation.getRentItem(),
                reservation.getRentPeriodStart(),
                reservation.getRentPeriodFinish(),
                reservation.getLessor(),
                reservation.getRenter()
        );
    }

    public static Reservation convertReservationDomainToReservation(ReservationDomain reservationDomain) {
        return new Reservation(
                reservationDomain.getDomainId(),
                reservationDomain.getRentItem(),
                reservationDomain.getRentPeriodStart(),
                reservationDomain.getRentPeriodFinish(),
                reservationDomain.getLessor(),
                reservationDomain.getRenter()
        );
    }

    public static ReservationView convertReservationDomainToReservationView(ReservationDomain reservationDomain) {
        return new ReservationView(
                reservationDomain.getDomainId(),
                reservationDomain.getRentItem(),
                reservationDomain.getRentPeriodStart(),
                reservationDomain.getRentPeriodFinish(),
                reservationDomain.getLessor(),
                reservationDomain.getRenter()
        );
    }
}
