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
import com.player32611.vo.SetmealPageVO;
import com.player32611.vo.SetmealVO;
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

    @Override
    public void status(Integer status, Long id){
        Setmeal setmeal = Setmeal.builder()
                .status(status)
                .id(id)
                .build();

        setmealMapper.update(setmeal);
    }

    @Override
    public SetmealVO id(Long id){
        Setmeal setmeal = setmealMapper.selectById(id);

        List<SetmealDish> setmealDishes = setmealDishMapper.selectBySetmealId(id);

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

    @Override
    public void update(SetmealDTO setmealDTO){
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmealMapper.update(setmeal);

        setmealDishMapper.deleteBySetmealId(setmealDTO.getId());

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes != null && !setmealDishes.isEmpty()){
            setmealDishes.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmealDTO.getId());
            });
            setmealDishMapper.insertBatch(setmealDishes);
        }
    }
}
