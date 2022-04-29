package pl.wszola.domain.validator;

import org.springframework.stereotype.Component;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReservationValidator {
    private final ReservationRepository reservationRepository;

    public ReservationValidator(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void checkReservationDateConflicts(LocalDate start, LocalDate finish, long itemId) {
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
}
