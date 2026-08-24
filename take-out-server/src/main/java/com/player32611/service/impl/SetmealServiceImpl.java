package com.player32611.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.player32611.dto.SetmealDTO;
import com.player32611.dto.SetmealPageDTO;
import com.player32611.entity.Setmeal;
import com.player32611.entity.SetmealDish;
import com.player32611.mapper.SetmealDishMapper;
import com.player32611.mapper.SetmealMapper;
import com.player32611.result.PageResult;
import com.player32611.service.SetmealService;
import com.player32611.vo.DishPageVO;
import com.player32611.vo.SetmealPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public void save(SetmealDTO setmealDTO){
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmealMapper.insert(setmeal);

        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes != null && !setmealDishes.isEmpty()){
            setmealDishes.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmealId);
            });
            setmealDishMapper.insertBatch(setmealDishes);
        }
    }

    @Override
    public PageResult<SetmealPageVO> page(SetmealPageDTO setmealPageDTO){
        PageHelper.startPage(setmealPageDTO.getPage(), setmealPageDTO.getPageSize());

        Page<SetmealPageVO> page = setmealMapper.page(setmealPageDTO);

        Long total = page.getTotal();
        List<SetmealPageVO> records = page.getResult();

        return new PageResult<>(total, records);
    }
}
