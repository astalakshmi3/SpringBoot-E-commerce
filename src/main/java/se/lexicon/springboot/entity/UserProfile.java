package se.lexicon.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString (onlyExplicitlyIncluded = true)
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table (name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    @Column (nullable = false, length = 20)
    @ToString.Include
    private String nickname;
    @Column (name = "phone_number", nullable = false, length = 12)
    @ToString.Include
    private String phoneNumber;
    @Column (nullable = false, length = 500)
    @ToString.Include
    private String bio;

    //Bidirectional
    @OneToOne (mappedBy = "userProfile")// Inverse side
    private  Customer customer;

}
