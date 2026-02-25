package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @ManyToOne (fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
}
