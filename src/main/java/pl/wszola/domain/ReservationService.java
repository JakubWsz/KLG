package pl.wszola.domain;

import org.springframework.stereotype.Service;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.persistence.ReservationRepository;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.infrastructure.entity.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationView makeReservation(RentItem rentItem, LocalDate rentPeriodStart,
                                           LocalDate rentPeriodFinish, Person lessor, Person renter) {

        checkReservationDateConflicts(rentPeriodStart, rentPeriodFinish, rentItem.getId());

        Reservation reservation = new Reservation(rentItem, rentPeriodStart, rentPeriodFinish, lessor, renter);
        return saveReservationAndReturnReservationView(reservation);
    }

    public ReservationView updateReservation(UpdateReservationRequest changeReservation, long id) {

        checkReservationDateConflicts(changeReservation.getRentPeriodStart(), changeReservation.getRentPeriodFinish(),
                changeReservation.getRentItem().getId());

        Reservation reservation = reservationRepository.getById(id);
        updateReservation(reservation, changeReservation);
        return saveReservationAndReturnReservationView(reservation);
    }

    public List<ReservationView> getAllReservationsByItemId(long id) {
        List<Reservation> reservations = reservationRepository.getAllByItemId(id);
        return getReservationViews(reservations);
    }

    public List<ReservationView> getAllReservationsByRenterId(long id) {
        List<Reservation> reservations = reservationRepository.getAllByRenterId(id);
        return getReservationViews(reservations);
    }

    private ReservationView mapReservationToReservationView(Reservation reservation) {
        return new ReservationView(
                reservation.getId(),
                reservation.getRentItem(),
                reservation.getRentPeriodStart(),
                reservation.getRentPeriodFinish(),
                reservation.getLessor(),
                reservation.getRenter()
        );
    }

    private void updateReservation(Reservation primaryReservation, UpdateReservationRequest updateReservation) {
        primaryReservation.setRentPeriodStart(updateReservation.getRentPeriodStart());
        primaryReservation.setRentPeriodFinish(updateReservation.getRentPeriodFinish());
    }

    private ReservationView saveReservationAndReturnReservationView(Reservation reservation) {
        reservationRepository.save(reservation);
        return mapReservationToReservationView(reservation);
    }

    private void checkReservationDateConflicts(LocalDate start, LocalDate finish, long itemId) {
        List<Reservation> reservations = reservationRepository.getAllByItemId(itemId);
        for (Reservation reservation : reservations) {
            if (reservation.getRentPeriodStart().isAfter(start) && reservation.getRentPeriodStart().isBefore(finish)
                    || reservation.getRentPeriodStart().isBefore(start) && reservation.getRentPeriodFinish().isAfter(finish)
                    || reservation.getRentPeriodStart().isBefore(start) && reservation.getRentPeriodFinish().isBefore(finish)
                    || reservation.getRentPeriodStart().isAfter(start) && reservation.getRentPeriodFinish().isAfter(finish)){
            throw new RuntimeException("Date conflict");
                }
        }
    }

    private List<ReservationView> getReservationViews(List<Reservation> reservations) {
        List<ReservationView> reservationsViews = new ArrayList<>();
        reservations.forEach(reservation -> reservationsViews.add(mapReservationToReservationView(reservation)));
        return reservationsViews;
    }
}
