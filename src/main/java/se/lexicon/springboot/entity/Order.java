package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column (nullable = false)
    @ToString.Include
    private Instant orderDate;
    @Enumerated (EnumType.STRING) // Store enum as String in the database
    @Column (nullable = false, length = 30)
    @ToString.Include
    private  OrderStatus status = OrderStatus.CREATED;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "customer_id", nullable = false)
    private  Customer customer;

    @PrePersist
    private void prePersist()
    {
        orderDate = Instant.now();
    }

    @OneToMany (mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // Convenience methods to manage bidirectional relationship with OrderItem
    public void addItem (OrderItem item)
    {
            if (item == null)
            {
                throw new  IllegalArgumentException("item cannot be null");
            }
            items.add(item);
            item.setOrder(this); // Set the back reference in OrderItem
    }

    public void removeItem (OrderItem item)
    {
        if (item == null)
        {
            throw new   IllegalArgumentException("item cannot be null");
        }
        items.remove(item);
        item.setOrder(null); // Remove the back reference in OrderItem
    }
}
