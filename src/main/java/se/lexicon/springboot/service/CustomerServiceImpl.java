package se.lexicon.springboot.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springboot.dto.request.CustomerRequest;
import se.lexicon.springboot.dto.response.CustomerResponse;
import se.lexicon.springboot.entity.Customer;
import se.lexicon.springboot.exceptions.ResourceNotFoundException;
import se.lexicon.springboot.mapper.CustomerMapper;
import se.lexicon.springboot.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public CustomerResponse register(CustomerRequest customerRequest) {
        if (customerRepository.existsByEmailIgnoreCase(customerRequest.email())) {
            throw new IllegalArgumentException("Email already exists: " + customerRequest.email());
        }
        //Convert CustomerRequest to Customer entity
        Customer customer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(savedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

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

        // Update the existing customer entity with the new data from the request
        customerMapper.updateEntity(existingCustomer, customerRequest);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerResponse(updatedCustomer);
    }
}