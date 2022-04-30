package pl.wszola.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wszola.api.request.MakeReservationRequest;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.reservation.model.ReservationDomain;
import pl.wszola.domain.reservation.ReservationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ConversionService conversionService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService, ConversionService conversionService) {
        this.reservationService = reservationService;
        this.conversionService = conversionService;
    }

    @PostMapping("/make")
    public ResponseEntity<ReservationView> makeReservation(@RequestBody MakeReservationRequest reservationRequest) {
        LOGGER.info("Starting reservation {}", reservationRequest);

        ReservationDomain reservationDomain = reservationService.makeReservation(
                reservationRequest.getRentItem(),
                reservationRequest.getRentPeriodStart(),
                reservationRequest.getRentPeriodFinish(),
                reservationRequest.getLessor(),
                reservationRequest.getRenter()
        );
        return new ResponseEntity<>(conversionService.convert(reservationDomain, ReservationView.class),
                HttpStatus.CREATED);
    }

    @PatchMapping("/update/")
    public ResponseEntity<ReservationView> updateReservation(@RequestBody UpdateReservationRequest changeReservation) {
        LOGGER.info("Starting update reservation {}", changeReservation);

        ReservationDomain reservationDomain = reservationService.updateReservation(changeReservation);
        return new ResponseEntity<>(conversionService.convert(reservationDomain, ReservationView.class),
                HttpStatus.OK);
    }

    @GetMapping("/get-all-renter-reservations/{renterId}")
    public List<ReservationView> getAllRenterReservations(@PathVariable long renterId) {
        LOGGER.info("Starting get renter reservation reservation list event {}", renterId);
        return mapReservationListToReservationViewList(reservationService.getAllReservationsByRenterId(renterId));
    }

    @GetMapping("/get-all-item-reservations/{itemId}")
    public List<ReservationView> getAllItemReservations(@PathVariable long itemId) {
        LOGGER.info("Starting get renter reservation reservation list event {}", itemId);
        return mapReservationListToReservationViewList(reservationService.getAllReservationsByItemId(itemId));
    }

    private List<ReservationView> mapReservationListToReservationViewList(List<ReservationDomain> reservations) {
        List<ReservationView> reservationsViews = new ArrayList<>();
        reservations.forEach(reservation -> reservationsViews.add(conversionService.convert(
                reservation, ReservationView.class)));
        return reservationsViews;
    }

}
