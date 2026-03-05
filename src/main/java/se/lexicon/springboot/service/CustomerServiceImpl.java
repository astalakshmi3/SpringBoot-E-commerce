package se.lexicon.springboot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.lexicon.springboot.dto.request.CustomerRequest;
import se.lexicon.springboot.dto.response.CustomerResponse;
import se.lexicon.springboot.entity.Customer;
import se.lexicon.springboot.exceptions.ResourceNotFoundException;
import se.lexicon.springboot.mapper.CustomerMapper;
import se.lexicon.springboot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerResponse register(CustomerRequest customerRequest) {
        if (customerRepository.existsByEmailIgnoreCase(customerRequest.email()))
        {
            throw new IllegalArgumentException("Email already exists: " + customerRequest.email());
        }
        Customer customer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(savedCustomer);
    }

    @Override
    @Transactional
    public CustomerResponse findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        return customerMapper.toCustomerResponse(customer);
    }
    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        // Check if the email is being updated and if the new email already exists for another customer
        if (!existingCustomer.getEmail().equalsIgnoreCase(customerRequest.email()) &&
                customerRepository.existsByEmailIgnoreCase(customerRequest.email())) {
            throw new IllegalArgumentException("Email already exists: " + customerRequest.email());
        }
        existingCustomer.setFirstName(customerRequest.firstName());
        existingCustomer.setEmail(customerRequest.email());
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerResponse(updatedCustomer);
    }
}
