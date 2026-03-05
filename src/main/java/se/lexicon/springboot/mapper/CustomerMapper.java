package se.lexicon.springboot.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.springboot.dto.request.CustomerRequest;
import se.lexicon.springboot.dto.response.CustomerResponse;
import se.lexicon.springboot.entity.Address;
import se.lexicon.springboot.entity.Customer;

@Component
public class CustomerMapper {
    public CustomerResponse toCustomerResponse (Customer customer) {
        if  (customer == null) throw  new IllegalArgumentException("Customer cannot be null");
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
    public Customer toEntity(CustomerRequest customerRequest) {
        if (customerRequest == null) throw new IllegalArgumentException("CustomerResponse is null");
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.firstName());
        customer.setEmail(customerRequest.email());
        //customer.setAddress(customerResponse.address());
        Address address = new Address();
        address.setStreet(customerRequest.street());
        address.setCity(customerRequest.city());
        address.setZipCode(customerRequest.zipCode());
        customer.setAddress(address);
        return customer;
    }
    public void updateEntity(Customer customer, CustomerRequest customerRequest) {
        if (customer == null) throw new IllegalArgumentException("Customer is null");
        if (customerRequest == null) throw new IllegalArgumentException("CustomerResponse is null");
        customer.setFirstName(customerRequest.firstName());
        customer.setEmail(customerRequest.email());
        //customer.setAddress(customerResponse.address());
        Address address = customer.getAddress();
        if (address == null) {
            address = new Address();
            customer.setAddress(address);
        }
        address.setStreet(customerRequest.street());
        address.setCity(customerRequest.city());
        address.setZipCode(customerRequest.zipCode());
    }
}
