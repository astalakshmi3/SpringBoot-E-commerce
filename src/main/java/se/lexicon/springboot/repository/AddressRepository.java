package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springboot.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByZipCode (String zipCode);
    List<Address> findByCityIgnoreCase (String city);
    List<Address> findByStreetContainingIgnoreCase(String street);
    long countByZipCode (String zipCode);
    List<Address> findAddressesByZipCodeStartingWithIgnoreCase (String prefix);
}
