package pl.wszola.api.rest;

import org.springframework.web.bind.annotation.*;
import pl.wszola.api.request.MakeReservationRequest;
import pl.wszola.api.request.UpdateReservationRequest;
import pl.wszola.api.response.ReservationView;
import pl.wszola.domain.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/make")
    public ReservationView makeReservation(@RequestBody MakeReservationRequest makeReservation) {
       return reservationService.makeReservation(
                makeReservation.getRentItem(),
                makeReservation.getRentPeriodStart(),
                makeReservation.getRentPeriodFinish(),
                makeReservation.getLessor(),
                makeReservation.getRenter()
        );
    }

    @PatchMapping("/update/{id}")
    public ReservationView updateReservation(@RequestBody UpdateReservationRequest changeReservation,
                                             @PathVariable long id){
        return reservationService.updateReservation(changeReservation,id);
    }

    @GetMapping("/get-all-renter-reservations/{renterId}")
    public List<ReservationView> getAllRenterReservations(@PathVariable long renterId){
        return reservationService.getAllReservationsByRenterId(renterId);
    }

    @GetMapping("/get-all-item-reservations/{itemId}")
    public List<ReservationView> getAllItemReservations(@PathVariable long itemId){
        return reservationService.getAllReservationsByItemId(itemId);
    }


}
