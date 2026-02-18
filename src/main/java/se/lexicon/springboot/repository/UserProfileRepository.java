package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springboot.entity.UserProfile;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
List<UserProfile> findByNicknameIgnoreCase (String nickname);
List<UserProfile> findByPhoneNumberIgnoreCase (String phoneNumber);
List<UserProfile> findByBioEmpty();
List<UserProfile> findByNicknameStartingWithIgnoreCase (String prefix);
long countByPhoneNumberStartingWith(String prefix);
}
