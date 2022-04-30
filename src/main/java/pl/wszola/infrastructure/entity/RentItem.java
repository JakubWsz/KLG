package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class RentItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal price;
    private double stretch;
    @OneToOne(fetch = FetchType.LAZY)
    private Person lessor;

    public RentItem(String name, BigDecimal price, double stretch, Person lessor) {
        this.name = name;
        this.price = price;
        this.stretch = stretch;
        this.lessor = lessor;
    }
}
