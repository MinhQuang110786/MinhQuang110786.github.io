package com.heaven.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int qty;
    private BigDecimal subtotal;

    @OneToOne
    private Book book;

    @OneToMany(mappedBy = "cartItem")
    @JsonIgnore
    private List<BookToCartItem> bookToCartItemList;

    @ManyToOne
    @JoinColumn(name="shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}
