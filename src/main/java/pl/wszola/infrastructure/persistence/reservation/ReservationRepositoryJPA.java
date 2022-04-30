package pl.wszola.infrastructure.persistence.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.List;

public interface ReservationRepositoryJPA extends JpaRepository<Reservation, String> {
    Reservation getById(String id);

    List<Reservation> getAllByRenterId(String renterId);

    List<Reservation> getAllByRentItemId(String itemId);

    Reservation getByRenterId(String renterId);
}
