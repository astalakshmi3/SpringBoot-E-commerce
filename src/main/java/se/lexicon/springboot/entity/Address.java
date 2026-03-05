package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table (name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column (nullable = false, length = 100)
    @ToString.Include
    private String street;
    @Column (nullable = false, length = 50)
    @ToString.Include
    private String city;
    @Column (name = "zip_code", nullable = false, length = 10)
    @ToString.Include
    private String zipCode;

    public String getFullAddress()
    {
        return String.format("%s, %s %s", street, zipCode, city);
    }
}
