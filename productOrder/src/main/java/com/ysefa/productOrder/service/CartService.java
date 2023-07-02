package com.ysefa.productOrder.service;

import com.ysefa.productOrder.entity.Cart;
import com.ysefa.productOrder.entity.CartItem;
import com.ysefa.productOrder.entity.Product;
import com.ysefa.productOrder.exception.NotFoundException;
import com.ysefa.productOrder.repository.CartRepository;
import com.ysefa.productOrder.repository.ProductRepository;
import com.ysefa.productOrder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;


    public void addToCart(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        Product product = getProductById(productId);

        CartItem cartItem = findCartItemByProductId(cart, productId);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);
    }

    private Product getProductById(Long productId) {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

    }
    public Cart getCartByUserId(Long userId){
        return cartRepository.findByUserId(userId);
    }

    public void updateCartItem(Long userId, Long cartItemId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        CartItem cartItem = findCartItemById(cart, cartItemId);

        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    public void removeFromCart(Long userId, Long cartItemId) {
        Cart cart = getOrCreateCart(userId);
        CartItem cartItem = findCartItemById(cart, cartItemId);

        if (cartItem != null) {
            cart.getCartItems().remove(cartItem);
            cartRepository.save(cart);
        }
    }

    public void checkout(Long userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        Optional<Cart> optionalCart = Optional.ofNullable(cartRepository.findByUserId(userId));
        return optionalCart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });
    }


    private CartItem findCartItemByProductId(Cart cart, Long productId) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    private CartItem findCartItemById(Cart cart, Long cartItemId) {
        return cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElse(null);
    }
}