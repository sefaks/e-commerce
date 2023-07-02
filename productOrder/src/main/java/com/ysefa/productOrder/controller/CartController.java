package com.ysefa.productOrder.controller;

import com.ysefa.productOrder.entity.Cart;
import com.ysefa.productOrder.request.CartItemRequest;
import com.ysefa.productOrder.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addToCart(@PathVariable Long userId, @RequestBody CartItemRequest cartItemRequest) {
        cartService.addToCart(userId, cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}/items/{cartItemId}")
    public ResponseEntity<Void> updateCartItem(@PathVariable Long userId, @PathVariable Long cartItemId, @RequestBody CartItemRequest cartItemRequest) {
        cartService.updateCartItem(userId, cartItemId, cartItemRequest.getQuantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/items/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        cartService.removeFromCart(userId, cartItemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<Void> checkout(@PathVariable Long userId) {
        cartService.checkout(userId);
        return ResponseEntity.ok().build();
    }
}