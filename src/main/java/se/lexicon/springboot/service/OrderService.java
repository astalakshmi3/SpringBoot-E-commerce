package se.lexicon.springboot.service;

import se.lexicon.springboot.dto.request.OrderRequest;
import se.lexicon.springboot.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
