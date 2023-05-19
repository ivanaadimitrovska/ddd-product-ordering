package com.example.orderorderitem.repository;

import com.example.orderorderitem.domain.models.Order;
import com.example.orderorderitem.domain.models.OrderID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderID> {
}
