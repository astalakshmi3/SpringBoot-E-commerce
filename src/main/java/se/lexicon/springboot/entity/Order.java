package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    private  OrderStatus status = OrderStatus.CREATED;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "customer_id")
    private  Customer customer;

    @PrePersist
    private void prePersist()
    {
        orderDate = Instant.now();
    }

    @OneToMany (mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public void addItem (OrderItem item)
    {
            if (item == null)
            {
                throw new  IllegalArgumentException("item cannot be null");
            }
            items.add(item);
    }

    public void removeItem (OrderItem item)
    {
        if (item == null)
        {
            throw new   IllegalArgumentException("item cannot be null");
        }
        items.remove(item);
    }
}
