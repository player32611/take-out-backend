package com.player32611.service;

import com.player32611.dto.CategoryDTO;
import com.player32611.dto.CategoryDeleteDTO;
import com.player32611.dto.CategoryListDTO;
import com.player32611.dto.CategoryPageDTO;
import com.player32611.entity.Category;
import com.player32611.result.PageResult;

import java.util.List;

public interface CategoryService {

    PageResult<Category> page(CategoryPageDTO categoryPageDTO);

    void add(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void status(Integer status, Long id);

    void delete(CategoryDeleteDTO categoryDeleteDTO);

    List<Category> list(CategoryListDTO categoryListDTO);
}
