package com.player32611.service;

import com.player32611.dto.ShoppingCartDTO;
import com.player32611.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> list();

    void clean();

    void sub(ShoppingCartDTO shoppingCartDTO);
}
