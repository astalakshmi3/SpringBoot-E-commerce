package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column (nullable = false, length = 100)
    @ToString.Include
    private String name;

    // Category to Products (bidirectional)
    @OneToMany (mappedBy = "category", fetch = FetchType.LAZY)
    private List <Product> products = new ArrayList<>();
}
