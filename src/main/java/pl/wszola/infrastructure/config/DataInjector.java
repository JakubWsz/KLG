package pl.wszola.infrastructure.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import pl.wszola.infrastructure.entity.Person;
import pl.wszola.infrastructure.entity.PersonType;
import pl.wszola.infrastructure.entity.RentItem;
import pl.wszola.infrastructure.entity.Reservation;
import pl.wszola.infrastructure.persistence.PersonRepositoryJPA;
import pl.wszola.infrastructure.persistence.RentItemRepositoryJPA;
import pl.wszola.infrastructure.persistence.reservation.ReservationRepositoryJPA;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataInjector {

    private final ReservationRepositoryJPA reservationRepositoryJPA;
    private final PersonRepositoryJPA personRepositoryJPA;
    private final RentItemRepositoryJPA rentItemRepositoryJPA;
    private final Person JAN_NOWAK_LESSOR = new Person("Jan","Nowak", PersonType.LESSOR);
    private final Person JERZY_MAK_LESSOR = new Person("Jerzy","Mak", PersonType.LESSOR);
    private final Person DANIEL_KWIATKOWSKI_RENTER =
            new Person("Daniel","Kwiatkowski", PersonType.RENTER);
    private final Person IRENEUSZ_KOWALSKI_RENTER =
            new Person("Ireneusz","Kowalski", PersonType.RENTER);
    private final RentItem GRZYBOWSKA_OFFICE = new RentItem("Biuro Grzybowksa 17",
            BigDecimal.valueOf(30000),70, JERZY_MAK_LESSOR);
    private final RentItem POGODNA_OFFICE = new RentItem("Biuro Pogodna 21",
            BigDecimal.valueOf(30000),70, JERZY_MAK_LESSOR);
    private final RentItem LIPOWA_OFFICE = new RentItem("Biuro Lipowa 17",
            BigDecimal.valueOf(30000),70, JAN_NOWAK_LESSOR);

    public DataInjector(ReservationRepositoryJPA reservationRepositoryJPA, PersonRepositoryJPA personRepositoryJPA,
                        RentItemRepositoryJPA rentItemRepositoryJPA) {
        this.reservationRepositoryJPA = reservationRepositoryJPA;
        this.personRepositoryJPA = personRepositoryJPA;
        this.rentItemRepositoryJPA = rentItemRepositoryJPA;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void injectPersons(){
        personRepositoryJPA.save(JAN_NOWAK_LESSOR);
        personRepositoryJPA.save(JERZY_MAK_LESSOR);
        personRepositoryJPA.save(DANIEL_KWIATKOWSKI_RENTER);
        personRepositoryJPA.save(IRENEUSZ_KOWALSKI_RENTER);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void injectRentItems(){
        rentItemRepositoryJPA.save(GRZYBOWSKA_OFFICE);
        rentItemRepositoryJPA.save(POGODNA_OFFICE);
        rentItemRepositoryJPA.save(LIPOWA_OFFICE);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void injectReservations(){
        reservationRepositoryJPA.save(new Reservation("111",POGODNA_OFFICE,
                LocalDate.of(2022, 8, 1),
                LocalDate.of(2023, 7, 31),
                DANIEL_KWIATKOWSKI_RENTER));
    }

}
