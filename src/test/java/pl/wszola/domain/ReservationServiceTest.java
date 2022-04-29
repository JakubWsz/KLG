package pl.wszola.domain;

import org.junit.jupiter.api.Test;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.domain.reservation.ReservationDomain;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.domain.reservation.ReservationService;
import pl.wszola.domain.validator.ReservationValidator;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.PersonType;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.mock.ReservationRepositoryMock;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    private final ReservationRepository repository = new ReservationRepositoryMock();
    private final ReservationValidator reservationValidator = new ReservationValidator(repository);
    private final ReservationService reservationService = new ReservationService(repository, reservationValidator);

    private static final Person LESSOR = new Person(1, "Jan", "Rodo", PersonType.LESSOR);
    private static final Person RENTER = new Person(2, "Anrzej", "Bokser", PersonType.RENTER);
    private static final RentItem RENT_ITEM = new RentItem(3L, "biuro", BigDecimal.valueOf(2700), 35.5);
    private static final LocalDate RENT_START = LocalDate.of(2022, 6, 12);
    private static final LocalDate RENT_FINISH = LocalDate.of(2022, 6, 17);


    @Test
    void shouldSetPassedReservationData() {
        //when
        ReservationDomain reservationDomain = reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH,
                LESSOR, RENTER);

        //then
        assertEquals(reservationDomain.getRentItem(), RENT_ITEM);
        assertEquals(reservationDomain.getRentPeriodStart(), RENT_START);
        assertEquals(reservationDomain.getRentPeriodFinish(), RENT_FINISH);
        assertEquals(reservationDomain.getLessor(), LESSOR);
        assertEquals(reservationDomain.getRenter(), RENTER);
    }

    @Test
    void shouldThrowsExceptionWhenIsDataConflict() {
        //when

        reservationService.makeReservation(RENT_ITEM, LocalDate.of(2022, 6, 11),
                LocalDate.of(2022, 6, 13), LESSOR, RENTER);

        //then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH, LESSOR, RENTER));
        assertEquals("Date conflict", runtimeException.getMessage());
    }

    @Test
    void domainReservationShouldBeUpdated() {
        //given
        LocalDate updateRentPeriodStart = LocalDate.of(2022, 7, 4);
        LocalDate updateRentPeriodFinish = LocalDate.of(2022, 7, 16);

        //when
        ReservationDomain reservationDomain = reservationService.makeReservation(RENT_ITEM, RENT_START, RENT_FINISH,
                LESSOR, RENTER);

        UpdateReservationRequest updateReservationRequest = new UpdateReservationRequest(RENT_ITEM,
                updateRentPeriodStart,
                updateRentPeriodFinish,
                RENTER);

        //then
        assertNotNull(updateReservationRequest);
        assertNotEquals(updateReservationRequest.getRentPeriodStart(), reservationDomain.getRentPeriodStart());
        assertNotEquals(updateReservationRequest.getRentPeriodFinish(), reservationDomain.getRentPeriodFinish());
        assertEquals(updateReservationRequest.getRentPeriodStart(), updateRentPeriodStart);
        assertEquals(updateReservationRequest.getRentPeriodFinish(), updateRentPeriodFinish);
    }
}