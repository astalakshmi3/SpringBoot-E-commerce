package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table (name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 100)
    private String street;
    @Column (nullable = false, length = 50)
    private String city;
    @Column (name = "zip_code", nullable = false, length = 10)
    private String zipCode;

}
