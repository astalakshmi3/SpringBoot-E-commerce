package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.springboot.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId (@Param("orderId") Long orderId);
    List<OrderItem> findByProductId (@Param("productId") Long productId);
    List<OrderItem> findByQuantityGreaterThan(Integer quantity);

}
