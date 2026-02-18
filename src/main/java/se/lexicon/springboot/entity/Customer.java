package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table (name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 50)
    private String firstName;
    @Column (length = 50)
    private String lastName;
    @Column (unique = true, nullable = false, length = 50)
    private String email;
    @Column (nullable = false, updatable = false)
    private Instant createdAt;

    @OneToOne
    @JoinColumn (name = "address_id", nullable = false)
    private  Address address;

    @OneToOne
    @JoinColumn (name = "user_profile_id")
    private   UserProfile userProfile;

    @PrePersist
    private void prePersist()
    {
        createdAt = Instant.now();
    }

}
