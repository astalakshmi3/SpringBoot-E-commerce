package se.lexicon.springboot.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springboot.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByEmail (String email);
    List<Customer> findByLastNameIgnoreCase(String lastName);
    List<Customer> findByAddressCityIgnoreCase(String address);
}
