package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RentPeriod {
    private LocalDateTime start;
    private LocalDateTime finish;
}
