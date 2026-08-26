package com.player32611.service.impl;

import com.player32611.context.BaseContext;
import com.player32611.dto.ShoppingCartDTO;
import com.player32611.entity.Dish;
import com.player32611.entity.Setmeal;
import com.player32611.entity.ShoppingCart;
import com.player32611.mapper.DishMapper;
import com.player32611.mapper.SetmealMapper;
import com.player32611.mapper.ShoppingCartMapper;
import com.player32611.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(ShoppingCartDTO shoppingCartDTO){
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        // 如果已存在数据，则修改数量
        if(list != null && !list.isEmpty()){
            ShoppingCart cart = list.getFirst();
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);
        } else {
            // 如果不存在数据，则插入
            Long dishId = shoppingCartDTO.getDishId();
            if(dishId != null){
                Dish dish = dishMapper.selectById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                Setmeal setmeal = setmealMapper.selectById(shoppingCartDTO.getSetmealId());
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> list(){
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .build();

        return shoppingCartMapper.list(shoppingCart);
    }

    @Override
    public void clean(){
        shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());
    }
}
