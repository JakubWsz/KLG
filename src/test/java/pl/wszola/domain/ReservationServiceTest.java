package pl.wszola.domain;

import org.junit.jupiter.api.Test;
import pl.wszola.api.request.ReservationRequest;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.domain.reservation.ReservationService;
import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.domain.reservation.validator.ReservationValidator;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.PersonType;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.mock.ReservationRepositoryMock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    private final ReservationRepository repository = new ReservationRepositoryMock();
    private final ReservationValidator validator = new ReservationValidator(repository);
    private final ReservationService reservationService = new ReservationService(repository, validator);

    private static final Person LESSOR = new Person(UUID.randomUUID().toString(),
            "Jan", "Rodo", PersonType.LESSOR);
    private static final Person RENTER = new Person(UUID.randomUUID().toString(),
            "Anrzej", "Bokser", PersonType.RENTER);
    private static final RentItem RENT_ITEM = new RentItem(UUID.randomUUID().toString(),
            "biuro", BigDecimal.valueOf(2700), 35.5, LESSOR);
    private static final LocalDate RENT_START = LocalDate.of(2022, 6, 12);
    private static final LocalDate RENT_FINISH = LocalDate.of(2022, 6, 17);


    @Test
    void shouldSetPassedReservationData() {
        //when
        ReservationDomain reservationDomain = reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH,
                RENTER);

        //then
        assertEquals(reservationDomain.getRentItem(), RENT_ITEM);
        assertEquals(reservationDomain.getRentPeriodStart(), RENT_START);
        assertEquals(reservationDomain.getRentPeriodFinish(), RENT_FINISH);
        assertEquals(reservationDomain.getRenter(), RENTER);
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflictCase1() {
        //when
        reservationService.makeReservation(RENT_ITEM, LocalDate.of(2022, 6, 11),
                LocalDate.of(2022, 6, 13), RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH, RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflictCase2() {
        //when
        reservationService.makeReservation(RENT_ITEM, LocalDate.of(2022, 5, 1),
                LocalDate.of(2022, 7, 30), RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH, RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflictCase3() {
        //when

        reservationService.makeReservation(RENT_ITEM, LocalDate.of(2022, 6, 14),
                LocalDate.of(2022, 6, 24), RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH, RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    @Test
    void domainReservationShouldBeUpdated() {
        //given
        LocalDate updateRentPeriodStart = LocalDate.of(2022, 7, 4);
        LocalDate updateRentPeriodFinish = LocalDate.of(2022, 7, 16);

       ReservationRequest updateReservationRequest = new ReservationRequest(RENT_ITEM,
                updateRentPeriodStart,
                updateRentPeriodFinish,
                RENTER);

        //when
        ReservationDomain reservation = reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH, RENTER);

        ReservationDomain updatedReservation = reservationService.updateReservation(updateReservationRequest);

        //then
        assertNotNull(updatedReservation);
        assertNotEquals(updatedReservation, reservation);
        assertNotEquals(updateReservationRequest.getRentPeriodFinish(), reservation.getRentPeriodFinish());
        assertNotEquals(updatedReservation.getRentPeriodStart(), reservation.getRentPeriodStart());
        assertEquals(updateReservationRequest.getRentPeriodStart(), updateRentPeriodStart);
        assertEquals(updateReservationRequest.getRentPeriodFinish(), updateRentPeriodFinish);
        assertEquals(updatedReservation.getDomainId(), reservation.getDomainId());
        assertEquals(updatedReservation.getRentItem(), reservation.getRentItem());
        assertEquals(updatedReservation.getRenter(), reservation.getRenter());
    }
}