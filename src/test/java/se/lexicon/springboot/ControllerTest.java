package se.lexicon.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.lexicon.springboot.dto.response.CustomerResponse;
import se.lexicon.springboot.entity.Customer;
import se.lexicon.springboot.mapper.CustomerMapper;
import se.lexicon.springboot.repository.CustomerRepository;
import se.lexicon.springboot.service.CustomerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Asta");
        customer.setLastName("Lakshmi");
        customer.setEmail("asta@gmail.com");
    }
    @Test
    @DisplayName("POST/api/v1/customers")
    void testFindById() throws Exception {
        when (customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        CustomerResponse result = customerService.findById(1L);
        System.out.println(result);
        assertNotNull (result);
        assertEquals (customer.getFirstName(), result.fullName());
        assertEquals (customer.getEmail(), result.email());
        verify(customerRepository).findById(1L);
    }
}
