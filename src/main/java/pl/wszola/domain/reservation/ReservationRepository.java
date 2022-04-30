package pl.wszola.domain.reservation;

import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation getById(long id);
    List<ReservationDomain> getAllByRenterId(long Id);
    List<ReservationDomain> getAllByItemId(long Id);
    ReservationDomain save (ReservationDomain reservationDomain);
    Reservation getByRenterId(long id);
}
