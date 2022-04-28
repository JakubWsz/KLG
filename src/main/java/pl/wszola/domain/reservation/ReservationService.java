package pl.wszola.domain.reservation;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.api.response.ReservationView;
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
    private ConversionService conversionService;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDomain makeReservation(RentItem rentItem, LocalDate rentPeriodStart,
                                             LocalDate rentPeriodFinish, Person lessor, Person renter) {

        checkReservationDateConflicts(rentPeriodStart, rentPeriodFinish, rentItem.getId());

        ReservationDomain reservationDomain = new ReservationDomain(UUID.randomUUID().toString(), rentItem,
                rentPeriodStart, rentPeriodFinish, lessor, renter);

        return saveReservationAndReturnDomainReservation(reservationDomain);
    }

    public ReservationDomain updateReservation(UpdateReservationRequest updateReservation) {

        checkReservationDateConflicts(updateReservation.getRentPeriodStart(), updateReservation.getRentPeriodFinish(),
                updateReservation.getRentItem().getId());

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
        Reservation reservation = conversionService.convert(reservationDomain, Reservation.class);
        reservationRepository.save(reservation);
        return reservationDomain;
    }

    private void checkReservationDateConflicts(LocalDate start, LocalDate finish, long itemId) {
        List<Reservation> reservations = reservationRepository.getAllByItemId(itemId);
        for (Reservation reservation : reservations) {
            if (reservation.getRentPeriodStart().isAfter(start) && reservation.getRentPeriodStart().isBefore(finish)
                    || reservation.getRentPeriodStart().isBefore(start) && reservation.getRentPeriodFinish().isAfter(finish)
                    || reservation.getRentPeriodStart().isBefore(start) && reservation.getRentPeriodFinish().isBefore(finish)
                    || reservation.getRentPeriodStart().isAfter(start) && reservation.getRentPeriodFinish().isAfter(finish)) {
                throw new RuntimeException("Date conflict");
            }
        }
    }

    private List<ReservationView> mapReservationListToReservationViewList(List<Reservation> reservations) {
        List<ReservationView> reservationsViews = new ArrayList<>();
        reservations.forEach(reservation -> reservationsViews.add(conversionService.convert(
                reservation, ReservationView.class)));
        return reservationsViews;
    }
}
