package com.player32611.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.player32611.context.BaseContext;
import com.player32611.dto.CategoryDTO;
import com.player32611.dto.CategoryDeleteDTO;
import com.player32611.dto.CategoryPageDTO;
import com.player32611.entity.Category;
import com.player32611.mapper.CategoryMapper;
import com.player32611.result.PageResult;
import com.player32611.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult<Category> page(CategoryPageDTO categoryPageDTO){
        PageHelper.startPage(categoryPageDTO.getPage(), categoryPageDTO.getPageSize());

        Page<Category> page;
        if(categoryPageDTO.getName() == null && categoryPageDTO.getType() == null) {
            page = (Page<Category>) categoryMapper.selectAll();
        } else {
            page = (Page<Category>) categoryMapper.selectByNameLikeAndType(categoryPageDTO.getName(), categoryPageDTO.getType());
        }

        Long total = page.getTotal();
        List<Category> records = page.getResult();

        return new PageResult<>(total, records);
    }

    @Override
    public void add(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        category.setStatus(0);

        categoryMapper.insert(category);
    }

    @Override
    public void update(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        categoryMapper.update(category);
    }

    @Override
    public void status(Integer status, Long id){
        Category category = Category.builder()
                .status(status)
                .id(id)
                .build();

        categoryMapper.update(category);
    }

    @Override
    public void delete(CategoryDeleteDTO categoryDeleteDTO){
        categoryMapper.delete(categoryDeleteDTO.getId());
    }
}
