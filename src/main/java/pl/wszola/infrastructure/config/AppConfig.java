package pl.wszola.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wszola.domain.reservation.ReservationRepository;
import pl.wszola.domain.reservation.ReservationService;
import pl.wszola.domain.reservation.validator.ReservationValidator;

@Configuration
public class AppConfig {

    @Bean
    public ReservationService reservationService (ReservationRepository reservationRepository,
                                                  ReservationValidator reservationValidator){
        return new ReservationService(reservationRepository, reservationValidator);
    }
}
