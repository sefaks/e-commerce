package com.ysefa.productOrder.repository;

import com.ysefa.productOrder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
