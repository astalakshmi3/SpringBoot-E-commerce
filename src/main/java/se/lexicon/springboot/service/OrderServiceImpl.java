package se.lexicon.springboot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.lexicon.springboot.dto.request.OrderRequest;
import se.lexicon.springboot.dto.response.OrderResponse;
import se.lexicon.springboot.entity.Customer;
import se.lexicon.springboot.entity.Order;
import se.lexicon.springboot.entity.OrderItem;
import se.lexicon.springboot.entity.OrderStatus;
import se.lexicon.springboot.mapper.OrderMapper;
import se.lexicon.springboot.repository.CustomerRepository;
import se.lexicon.springboot.repository.OrderRepository;
import se.lexicon.springboot.repository.ProductRepository;

import java.util.ArrayList;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + orderRequest.customerId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(new ArrayList<>());
        order.setStatus(OrderStatus.CREATED);// change to CREATED when order is placed

        // Add order items
        orderRequest.orderItems().forEach(itemRequest -> {
            var product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + itemRequest.productId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());

            // Capture the price at the time of purchase
            orderItem.setPriceAtPurchase(product.getPrice());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
    });

        Order saveOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(saveOrder);
    }
}

