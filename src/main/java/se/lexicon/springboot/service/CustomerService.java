package se.lexicon.springboot.service;

import se.lexicon.springboot.dto.request.CustomerRequest;
import se.lexicon.springboot.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse register (CustomerRequest customerRequest);
    CustomerResponse findById (Long id);
    CustomerResponse update (Long id, CustomerRequest customerRequest);
}
