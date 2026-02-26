package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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

    // Order to OrderItem (Bidirectional)
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id")
    private Order order;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "product_id")
    private Product product;
}