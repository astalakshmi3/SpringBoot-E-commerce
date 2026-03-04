package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString (onlyExplicitlyIncluded = true) // Safe equals and toString no relatonships
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table (name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column (length = 50, nullable = false)
    @ToString.Include
    private String firstName;
    @Column (length = 50, nullable = false)
    @ToString.Include
    private String lastName;
    @Column (unique = true, nullable = false, length = 50)
    @ToString.Include
    private String email;
    @Column (nullable = false, updatable = false)
    @ToString .Include
    private Instant createdAt;

    @OneToOne (fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL, orphanRemoval = true)// Customer owns Address lifecycle
    @JoinColumn (name = "address_id", nullable = false) // FK in Customer table
    private  Address address; // Not include in toString and equals and hashcode because of potential circular reference

    @OneToOne (fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "user_profile_id")
    private   UserProfile userProfile;

    //bidirectional relationship with profile, customer is the owner of the relationship, cascade all operations, if customer is deleted, profile is also deleted
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        if (userProfile != null) {
            userProfile.setCustomer(this); // Set the back reference in UserProfile
        }

        // Set new linked profile's customer reference to this customer
        this.userProfile = userProfile;

        //Maintain inverse relationship, if userProfile is not null, set this customer as the owner of the profile
        if (userProfile != null) {
            userProfile.setCustomer(this);
        }
    }
    @PrePersist
    private void prePersist()
    {
        createdAt = Instant.now();
    }

}
