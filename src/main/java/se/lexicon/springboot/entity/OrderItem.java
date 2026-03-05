package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table (name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long Id;
    @Column (nullable = false)
    @ToString.Include
    private Integer quantity;
    @Column (nullable = false, precision = 10, scale = 2)
    @ToString.Include
    private BigDecimal priceAtPurchase;

    // Order to OrderItem (Bidirectional)
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id", nullable = false)
    private Order order;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "product_id", nullable = false)
    private Product product;
}