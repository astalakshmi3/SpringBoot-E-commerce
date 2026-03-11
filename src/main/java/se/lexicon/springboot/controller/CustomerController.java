package se.lexicon.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.data.DataRestResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springboot.dto.request.CustomerRequest;
import se.lexicon.springboot.dto.response.CustomerResponse;
import se.lexicon.springboot.entity.Customer;
import se.lexicon.springboot.service.CustomerService;

import java.util.List;

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
        System.out.println("Request body =" + customerRequest);
        CustomerResponse createdCustomer = customerService.register( customerRequest);
        System.out.println("Created customer ==> " + createdCustomer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCustomer);
    }

    @GetMapping ("/{id}")
    @Operation (summary = "Find by Customer ID")
    @Tag(name = "Customer ID")
    public ResponseEntity<CustomerResponse> findById (@PathVariable @Positive Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

   @PutMapping ("/{id}")
    public ResponseEntity<CustomerResponse> update (@PathVariable @Positive Long id,@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedCustomer = customerService.update(id, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
   }
}
