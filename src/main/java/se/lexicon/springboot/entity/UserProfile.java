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
@Table (name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 20)
    private String nickname;
    @Column (name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;
    @Column (nullable = false, length = 500)
    private String bio;

    @OneToOne (mappedBy = "userprofile")
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
