package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table (name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column(length = 100)
    @ToString.Include
    private String name;
    @ToString.Include
    private BigDecimal discountPercentage;
    @Column (nullable = false)
    @ToString.Include
    private LocalDate startDate;
    @Column (nullable = false)
    @ToString.Include
    private LocalDate endDate;
    @ToString.Include
    @EqualsAndHashCode.Include
    private boolean active;

    // Bidirection
    @ManyToMany (mappedBy = "promotions", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}
