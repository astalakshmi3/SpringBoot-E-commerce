package se.lexicon.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springboot.dto.request.CustomerRequest;
import se.lexicon.springboot.dto.response.CustomerResponse;
import se.lexicon.springboot.entity.Customer;
import se.lexicon.springboot.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@Validated
@Tag(name = "Customer Controller", description = "API for managing customer registration")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation (summary = "Register the customer information")
    @Tag(name = "Customer Registration")
    public ResponseEntity<CustomerResponse> register (@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse createdCustomer = customerService.register( customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping ("/{id}")
    @Operation (summary = "Find by Customer ID")
    @Tag(name = "Customer ID")
    public ResponseEntity<CustomerResponse> findById (@PathVariable @Positive Long id) {
        return  ResponseEntity.ok(customerService.findById(id));
    }
}
