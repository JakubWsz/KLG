package pl.wszola.mock;

import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryMock implements ReservationRepository {
    List<Reservation> reservations = new ArrayList<>();


    @Override
    public ReservationDomain save(ReservationDomain reservationDomain) {
        Reservation reservation = mapReservationDomainToReservation(reservationDomain);
        reservations.add(reservation);
        return reservationDomain;
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
    public List<ReservationDomain> getAllByRenterId(long id) {
        List<ReservationDomain> domainReservations = new ArrayList<>();
        reservations.forEach(reservation -> domainReservations.add(mapReservationToDomainReservation(reservation)));
        return domainReservations.stream()
                .filter(reservation -> reservation.getRenter().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDomain> getAllByItemId(long id) {
        List<ReservationDomain> domainReservations = new ArrayList<>();
        reservations.forEach(reservation -> domainReservations.add(mapReservationToDomainReservation(reservation)));
        return domainReservations.stream()
                .filter(reservationDomain -> reservationDomain.getRentItem().getId() == id)
                .collect(Collectors.toList());
    }

    private ReservationDomain mapReservationToDomainReservation(Reservation reservation) {
        return new ReservationDomain(
                reservation.getDomainId(),
                reservation.getRentItem(),
                reservation.getRentPeriodStart(),
                reservation.getRentPeriodFinish(),
                reservation.getLessor(),
                reservation.getRenter()
        );
    }

    private Reservation mapReservationDomainToReservation(ReservationDomain reservationDomain) {
        return new Reservation(
                reservationDomain.getDomainId(),
                reservationDomain.getRentItem(),
                reservationDomain.getRentPeriodStart(),
                reservationDomain.getRentPeriodFinish(),
                reservationDomain.getLessor(),
                reservationDomain.getRenter()
        );
    }
}
