package pl.wszola.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.wszola.infrastructure.converter.ReservationDomainToReservation;
import pl.wszola.infrastructure.converter.ReservationDomainToReservationView;
import pl.wszola.infrastructure.converter.ReservationToReservationDomain;

@Configuration
public class SpringConvertersConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ReservationDomainToReservation());
        registry.addConverter(new ReservationDomainToReservationView());
        registry.addConverter(new ReservationToReservationDomain());
    }
}