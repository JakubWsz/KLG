package pl.wszola.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import pl.wszola.domain.persistence.ReservationRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepository {
    private final ReservationRepositoryJPA reservationRepositoryJPA;

    public ReservationRepositoryAdapter(ReservationRepositoryJPA reservationRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
    }

    @Override
    public Reservation getById(long id) {
        return reservationRepositoryJPA.getById(id);
    }

    @Override
    public List<Reservation> getAllByRenterId(long id) {
        return reservationRepositoryJPA.getAllByRenterId(id);
    }

    @Override
    public List<Reservation> getAllByItemId(long id) {
        return reservationRepositoryJPA.getAllByRentItemId(id);
    }


    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepositoryJPA.save(reservation);
    }
}
