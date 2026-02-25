package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private Instant orderDate;
    @Enumerated (EnumType.STRING)
    private  OrderStatus status;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "customer_id")
    private  Customer customer;

    @PrePersist
    private void prePersist()
    {
        orderDate = Instant.now();
    }

}
