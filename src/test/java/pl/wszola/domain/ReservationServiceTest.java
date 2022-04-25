package pl.wszola.domain;

import org.junit.jupiter.api.Test;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.persistence.ReservationRepository;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.PersonType;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.infrastructure.entity.Reservation;
import pl.wszola.mock.ReservationRepositoryMock;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationServiceTest {
    
    private static final Person LESSOR = new Person(1,"Jan","Rodo", PersonType.LESSOR);
    private static final Person RENTER = new Person(2,"Anrzej","Bokser", PersonType.RENTER);
    private static final RentItem RENT_ITEM = new RentItem(3L,"biuro", BigDecimal.valueOf(2700),35.5);
    private static final LocalDate RENT_START = LocalDate.of(2022,6,12);
    private static final LocalDate RENT_FINISH = LocalDate.of(2022,6,17);

    private final ReservationRepository repository = new ReservationRepositoryMock();
    private final ReservationService reservationService =new ReservationService(repository);


    @Test
    void shouldSetPassedReservationData() {
        //when
        ReservationView reservationView = reservationService.makeReservation(RENT_ITEM,RENT_START,RENT_FINISH,
                LESSOR,RENTER);
        Reservation reservation = mapReservationViewToReservation(reservationView);

        //then
        assertEquals(reservation.getRentItem(),RENT_ITEM );
        assertEquals(reservation.getRentPeriodStart(),RENT_START);
        assertEquals(reservation.getRentPeriodFinish(),RENT_FINISH);
        assertEquals(reservation.getLessor(),LESSOR);
        assertEquals(reservation.getRenter(),RENTER);
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflictCase1(){
        //when

        reservationService.makeReservation(RENT_ITEM,LocalDate.of(2022,6,11),
                LocalDate.of(2022,6,13),LESSOR,RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM,RENT_START,RENT_FINISH,LESSOR,RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflictCase2(){
        //when

        reservationService.makeReservation(RENT_ITEM,LocalDate.of(2022,6,1),
                LocalDate.of(2022,6,31),LESSOR,RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM,RENT_START,RENT_FINISH,LESSOR,RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflictCase3(){
        //when

        reservationService.makeReservation(RENT_ITEM,LocalDate.of(2022,6,14),
                LocalDate.of(2022,6,24),LESSOR,RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM,RENT_START,RENT_FINISH,LESSOR,RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    private Reservation mapReservationViewToReservation( ReservationView reservationView) {
        return new Reservation(
                reservationView.getId(),
                reservationView.getRentItem(),
                reservationView.getRentPeriodStart(),
                reservationView.getRentPeriodFinish(),
                reservationView.getLessor(),
                reservationView.getRenter()
        );
    }

}