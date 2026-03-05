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
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column(length = 100, nullable = false)
    @ToString.Include
    private String name;
    @Column(length = 100, nullable = false)
    @ToString.Include
    private BigDecimal price;
    @ElementCollection
    @CollectionTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    // Product to Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_promtions",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private Set<Promotion> promotions = new HashSet<>();

   // public Product(String laptop, BigDecimal bigDecimal, Category electronics) {
    //}
    // Helper method for bidirectional relationship
    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
        promotion.getProducts().add(this);
    }

    public void  removePromotion(Promotion promotion) {
        this.promotions.remove(promotion);
        promotion.getProducts().remove(this);
    }
}
