package com.ibm.sales.repository;

import com.ibm.sales.model.Order;
import com.ibm.sales.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {

    List<OrderProduct> getAllProductByOrderID(@Param("OrderID") UUID OrderID);

}