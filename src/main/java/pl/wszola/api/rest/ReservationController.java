package pl.wszola.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wszola.api.request.MakeReservationRequest;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.reservation.ReservationDomain;
import pl.wszola.domain.reservation.ReservationService;
import pl.wszola.infrastructure.converter.Converter;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/make")
    public ResponseEntity<ReservationView> makeReservation(@RequestBody MakeReservationRequest reservationRequest) {
        LOGGER.info("Starting reservation event {}", reservationRequest);

        ReservationDomain reservationDomain = reservationService.makeReservation(
                reservationRequest.getRentItem(),
                reservationRequest.getRentPeriodStart(),
                reservationRequest.getRentPeriodFinish(),
                reservationRequest.getLessor(),
                reservationRequest.getRenter()
        );
        return new ResponseEntity<>(Converter.convertReservationDomainToReservationView(reservationDomain),
                HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<ReservationView> updateReservation(@RequestBody UpdateReservationRequest changeReservation) {
        LOGGER.info("Starting update reservation event {}", changeReservation);

        ReservationDomain reservationDomain = reservationService.updateReservation(changeReservation);
        return new ResponseEntity<>(Converter.convertReservationDomainToReservationView(reservationDomain),
                HttpStatus.OK);
    }

    @GetMapping("/get-all-renter-reservations/{renterId}")
    public List<ReservationView> getAllRenterReservations(@PathVariable long renterId) {
        LOGGER.info("Starting get all renter reservation event {}", renterId);
        return reservationService.getAllReservationsByRenterId(renterId);
    }

    @GetMapping("/get-all-item-reservations/{itemId}")
    public List<ReservationView> getAllItemReservations(@PathVariable long itemId) {
        LOGGER.info("Starting get all item reservation event {}", itemId);
        return reservationService.getAllReservationsByItemId(itemId);
    }
}
