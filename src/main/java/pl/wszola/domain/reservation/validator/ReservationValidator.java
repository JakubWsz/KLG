package pl.wszola.domain.reservation.validator;

import org.springframework.stereotype.Component;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.domain.reservation.model.ReservationDomain;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReservationValidator {
    private final ReservationRepository reservationRepository;

    public ReservationValidator(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void validateReservationDateConflicts(LocalDate start, LocalDate finish, String itemId) {
        List<ReservationDomain> reservations = reservationRepository.getAllByItemId(itemId);
        for (ReservationDomain reservation : reservations) {

            if (dataComparator(start,reservation.getRentPeriodStart(),reservation.getRentPeriodFinish())
            || dataComparator(finish,reservation.getRentPeriodStart(),reservation.getRentPeriodFinish())){
                throw new RuntimeException("Date conflict");
            }
        }
    }

    private boolean dataComparator(LocalDate date, LocalDate start, LocalDate finish){
        return start.compareTo(date) * date.compareTo(finish) > 0;
    }
}