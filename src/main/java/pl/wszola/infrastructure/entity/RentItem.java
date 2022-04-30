package pl.wszola.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String name;
    private BigDecimal price;
    private double stretch;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Person lessor;

    public RentItem(String name, BigDecimal price, double stretch, Person lessor) {
        this.name = name;
        this.price = price;
        this.stretch = stretch;
        this.lessor = lessor;
    }
}
