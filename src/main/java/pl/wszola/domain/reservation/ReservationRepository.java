package pl.wszola.domain.reservation;

import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation getById(String id);
    List<ReservationDomain> getAllByRenterId(String renterId);
    List<ReservationDomain> getAllByItemId(String itemId);
    ReservationDomain save (ReservationDomain reservationDomain);
    Reservation getByRenterId(String renterId);
}
