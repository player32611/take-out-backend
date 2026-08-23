package com.player32611.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.player32611.dto.DishDTO;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Dish;
import com.player32611.entity.DishFlavor;
import com.player32611.entity.Employee;
import com.player32611.mapper.DishFlavorMapper;
import com.player32611.mapper.DishMapper;
import com.player32611.result.PageResult;
import com.player32611.service.DishService;
import com.player32611.vo.DishPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional
    public void save(DishDTO dishDTO){
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.insert(dish);

        Long dishId = dish.getId();

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult<DishPageVO> page(DishPageDTO dishPageDTO){
        PageHelper.startPage(dishPageDTO.getPage(), dishPageDTO.getPageSize());

        Page<DishPageVO> page = dishMapper.page(dishPageDTO);

        Long total = page.getTotal();
        List<DishPageVO> records = page.getResult();

        return new PageResult<DishPageVO>(total, records);
    }
}
