package pl.wszola.domain.reservation;

import pl.wszola.infrastructure.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation getById(long id);
    List<Reservation> getAllByRenterId(long Id);
    List<Reservation> getAllByItemId(long Id);
    Reservation save (Reservation reservation);
    Reservation getByRenterId(long id);
}
