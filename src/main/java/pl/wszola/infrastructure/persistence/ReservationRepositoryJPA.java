package pl.wszola.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.List;

public interface ReservationRepositoryJPA extends JpaRepository<Reservation, Long> {
    Reservation getById(long id);

    List<Reservation> getAllByRenterId(long renterId);

    List<Reservation> getAllByRentItemId(long itemId);

    Reservation getByRenterId(long renterId);
}
