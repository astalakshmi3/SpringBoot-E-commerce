package se.lexicon.springboot.repository;

import jakarta.persistence.PrePersist;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springboot.entity.Customer;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail (String email);
    List<Customer> findByLastNameIgnoreCase(String lastName);
    List<Customer> findByAddressCityIgnoreCase (String city);

    List<Customer> findByEmailContainingIgnoreCase (String keyword );
    List<Customer> findByCreatedAtAfter(Instant date);
    List<Customer> findByCreatedAtBetween(Instant start, Instant end);
    long countByAddressCityIgnoreCase (String city);
    boolean existsByEmail (String email);

}
