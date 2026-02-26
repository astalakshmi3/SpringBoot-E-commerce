package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String code;
    @Column (nullable = false)
    private LocalDate startDate;
    @Column (nullable = false)
    private LocalDate endDate;

    // Bidirection al
    @ManyToMany (mappedBy = "promotions", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}
