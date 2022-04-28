package pl.wszola.mock;

import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryMock implements ReservationRepository {
    List<Reservation> reservations = new ArrayList<>();

    @Override
    public Reservation save(Reservation reservation) {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Reservation getByRenterId(long id) {
        return reservations.stream()
                .filter(reservation -> id == reservation.getRenter().getId())
                .findFirst()
                .get();
    }

    @Override
    public Reservation getById(long id) {
        return reservations.stream()
                .filter(reservation -> id == reservation.getId())
                .findFirst()
                .get();
    }

    @Override
    public List<Reservation> getAllByRenterId(long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getRenter().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> getAllByItemId(long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getRentItem().getId() == id)
                .collect(Collectors.toList());
    }
}
