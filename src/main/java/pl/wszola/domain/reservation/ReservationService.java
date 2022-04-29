package pl.wszola.domain.reservation;

import org.springframework.stereotype.Service;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.validator.ReservationValidator;
import pl.wszola.infrastructure.converter.Converter;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.infrastructure.entity.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationValidator reservationValidator;

    public ReservationService(ReservationRepository reservationRepository, ReservationValidator reservationValidator) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
    }

    public ReservationDomain makeReservation(RentItem rentItem, LocalDate rentPeriodStart,
                                             LocalDate rentPeriodFinish, Person lessor, Person renter) {
        reservationValidator.checkReservationDateConflicts(rentPeriodStart, rentPeriodFinish, rentItem.getId());

        ReservationDomain reservationDomain = new ReservationDomain(UUID.randomUUID().toString(), rentItem,
                rentPeriodStart, rentPeriodFinish, lessor, renter);
        return saveReservationAndReturnDomainReservation(reservationDomain);
    }

    public ReservationDomain updateReservation(UpdateReservationRequest updateReservation) {
        reservationValidator.checkReservationDateConflicts(updateReservation.getRentPeriodStart(),
                updateReservation.getRentPeriodFinish(), updateReservation.getRentItem().getId());

        Reservation reservation = reservationRepository.getByRenterId(updateReservation.getRenter().getId());
        ReservationDomain updatedReservation = new ReservationDomain(
                reservation.getDomainId(),
                reservation.getRentItem(),
                updateReservation.getRentPeriodStart(),
                updateReservation.getRentPeriodFinish(),
                reservation.getLessor(),
                reservation.getRenter()
        );
        return saveReservationAndReturnDomainReservation(updatedReservation);
    }

    public List<ReservationView> getAllReservationsByItemId(long id) {
        List<Reservation> reservations = reservationRepository.getAllByItemId(id);
        return mapReservationListToReservationViewList(reservations);
    }

    public List<ReservationView> getAllReservationsByRenterId(long id) {
        List<Reservation> reservations = reservationRepository.getAllByRenterId(id);
        return mapReservationListToReservationViewList(reservations);
    }

    private ReservationDomain saveReservationAndReturnDomainReservation(ReservationDomain reservationDomain) {
        Reservation reservation = Converter.convertReservationDomainToReservation(reservationDomain);
        reservationRepository.save(reservation);
        return reservationDomain;
    }

    private List<ReservationView> mapReservationListToReservationViewList(List<Reservation> reservations) {
        List<ReservationView> reservationsViews = new ArrayList<>();
        reservations.forEach(reservation -> reservationsViews.add(
                Converter.convertReservationToReservationView(reservation)));
        return reservationsViews;
    }
}
