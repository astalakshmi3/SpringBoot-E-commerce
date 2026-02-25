package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table (name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long Id;
    @Column (nullable = false)
    private Integer quantity;
    @Column (nullable = false)
    private BigDecimal priceAtPurchase;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "order_id", nullable = false)
    private Order order;

}
