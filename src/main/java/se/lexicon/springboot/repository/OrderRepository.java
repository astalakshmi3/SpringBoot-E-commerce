package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.springboot.entity.Order;
import se.lexicon.springboot.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId (Long customerId);
    // order by status
    //@Query ("SELECT o FROM Order o WHERE o.status = : status ")
    @Query ("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items i LEFT JOIN FETCH i.product WHERE o.status = :status")
    List<Order> findByStatusWithinOrderByItems (@Param("status") OrderStatus status);
    @Query ("SELECT o FROM Order o WHERE o.orderDate > : date")
    List<Order> findByDate (@Param("date") Instant date);
    @Query ("SELECT o FROM Order o WHERE o.orderDate BETWEEN : start AND :end")
    List<Order> findByOrderDateBetween (@Param("start") Instant start, @Param("end") Instant end);
    @Query ("SELECT DISTINCT o FROM Order o JOIN o.items i WHERE i.product.id = : productId")
    List<Order> findByIdContains (@Param("productId") Long productId);
    long countByStatus (@Param("status") OrderStatus status);
    List<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status);
}
