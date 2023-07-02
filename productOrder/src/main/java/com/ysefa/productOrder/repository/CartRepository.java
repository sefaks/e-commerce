package com.ysefa.productOrder.repository;

import com.ysefa.productOrder.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long userId);
}
