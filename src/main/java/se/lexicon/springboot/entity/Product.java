package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 100, nullable = false)
    private String name;
    @Column (length = 100, nullable = false)
    private BigDecimal price;
    @Column (nullable = false)
    private List <String> imageUrls = new ArrayList<>();

    // Product to Category
    @ManyToOne (fetch =  FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "products_promotions", joinColumns = @JoinColumn (name = "product_id"),inverseJoinColumns = @JoinColumn (name = "promotion_id"))
    private Set<Promotion> promotions = new HashSet<>();
}
