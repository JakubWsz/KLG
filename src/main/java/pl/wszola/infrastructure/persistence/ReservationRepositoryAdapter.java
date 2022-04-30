package pl.wszola.infrastructure.persistence;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.infrastructure.entity.Reservation;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepository {
    private final ReservationRepositoryJPA reservationRepositoryJPA;
    private final ConversionService conversionService;

    public ReservationRepositoryAdapter(ReservationRepositoryJPA reservationRepositoryJPA,
                                        ConversionService conversionService) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
        this.conversionService = conversionService;
    }

    @Override
    public Reservation getById(long id) {
        return reservationRepositoryJPA.getById(id);
    }

    @Override
    public List<ReservationDomain> getAllByRenterId(long id) {
        return mapReservationListToReservationDomainList(reservationRepositoryJPA.getAllByRenterId(id));
    }

    @Override
    public List<ReservationDomain> getAllByItemId(long id) {
        return mapReservationListToReservationDomainList(reservationRepositoryJPA.getAllByRentItemId(id));
    }

    @Override
    public ReservationDomain save(ReservationDomain reservationDomain) {
        reservationRepositoryJPA.save(conversionService.convert(reservationDomain, Reservation.class));
        return reservationDomain;
    }

    @Override
    public Reservation getByRenterId(long id) {
        return reservationRepositoryJPA.getByRenterId(id);
    }

    private List<ReservationDomain> mapReservationListToReservationDomainList(List<Reservation> reservations) {
        List<ReservationDomain> domainReservations = new ArrayList<>();
        reservations.forEach(reservation -> domainReservations.add(conversionService.convert(
                reservation, ReservationDomain.class)));
        return domainReservations;
    }
}
