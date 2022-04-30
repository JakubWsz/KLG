package pl.wszola.domain.reservation;

import pl.wszola.api.request.ReservationRequest;
import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.domain.reservation.validator.ReservationValidator;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.infrastructure.entity.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    public ReservationService(ReservationRepository reservationRepository, ReservationValidator reservationValidator) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
    }

    public ReservationDomain makeReservation(RentItem rentItem, LocalDate rentPeriodStart,
                                             LocalDate rentPeriodFinish, Person renter) {
        reservationValidator.validateReservationDateConflicts(rentPeriodStart, rentPeriodFinish, rentItem.getId());

        ReservationDomain reservationDomain = new ReservationDomain(UUID.randomUUID().toString(), rentItem,
                rentPeriodStart, rentPeriodFinish, renter);
        return reservationRepository.save(reservationDomain);
    }

    public ReservationDomain updateReservation(ReservationRequest updateReservation) {
        reservationValidator.validateReservationDateConflicts(updateReservation.getRentPeriodStart(),
                updateReservation.getRentPeriodFinish(), updateReservation.getRentItem().getId());

        Reservation reservation = reservationRepository.getByRenterId(updateReservation.getRenter().getId());
        ReservationDomain updatedReservation = new ReservationDomain(
                reservation.getDomainId(),
                reservation.getRentItem(),
                updateReservation.getRentPeriodStart(),
                updateReservation.getRentPeriodFinish(),
                reservation.getRenter()
        );
        reservationRepository.save(updatedReservation);
        return updatedReservation;
    }

    public List<ReservationDomain> getAllReservationsByItemId(String itemId) {
        return reservationRepository.getAllByItemId(itemId);
    }

    public List<ReservationDomain> getAllReservationsByRenterId(String renterId) {
        return reservationRepository.getAllByRenterId(renterId);
    }
}