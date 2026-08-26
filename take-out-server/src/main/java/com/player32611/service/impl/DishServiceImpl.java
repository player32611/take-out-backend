package com.player32611.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.player32611.constant.MessageConstant;
import com.player32611.constant.StatusConstant;
import com.player32611.dto.DishDTO;
import com.player32611.dto.DishListDTO;
import com.player32611.dto.DishPageDTO;
import com.player32611.entity.Category;
import com.player32611.entity.Dish;
import com.player32611.entity.DishFlavor;
import com.player32611.exception.DeletionNotAllowedException;
import com.player32611.exception.UpdateNotAllowedException;
import com.player32611.mapper.CategoryMapper;
import com.player32611.mapper.DishFlavorMapper;
import com.player32611.mapper.DishMapper;
import com.player32611.mapper.SetmealDishMapper;
import com.player32611.result.PageResult;
import com.player32611.service.DishService;
import com.player32611.vo.DishPageVO;
import com.player32611.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private CategoryMapper categoryMapper;

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

        return new PageResult<>(total, records);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids){
        for (Long id : ids){
            Dish dish = dishMapper.selectById(id);
            if(Objects.equals(dish.getStatus(), StatusConstant.ENABLE)){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        List<Long> setmealIds = setmealDishMapper.selectSetmealIdByDishIds(ids);
        if(setmealIds != null && !setmealIds.isEmpty()){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);
    }

    @Override
    public DishVO id(Long id){
        Dish dish = dishMapper.selectById(id);

        List<DishFlavor> dishFlavors = dishFlavorMapper.selectByDishId(id);

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);

        return dishVO;
    }

    @Override
    public void update(DishDTO dishDTO){
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.update(dish);

        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public void status(Integer status, Long id){
        List<Long> setmealIds = setmealDishMapper.selectSetmealIdByDishId(id);
        if(setmealIds != null && !setmealIds.isEmpty()){
            throw new UpdateNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL_ENABLE);
        }

        Dish dish = Dish.builder()
                .status(status)
                .id(id)
                .build();

        dishMapper.update(dish);
    }

    @Override
    public List<DishVO> list(DishListDTO dishListDTO) {
        Category category = categoryMapper.selectById(dishListDTO.getCategoryId());
        List<Dish> dishList = dishMapper.selectByCategoryId(dishListDTO.getCategoryId());

        List<DishVO> dishVOList = new ArrayList<>();
        for (Dish dish : dishList) {
            List<DishFlavor> dishFlavors = dishFlavorMapper.selectByDishId(dish.getId());
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(dish, dishVO);
            dishVO.setFlavors(dishFlavors);
            dishVO.setCategoryName(category.getName());
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }
}
