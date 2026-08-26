package com.player32611.controller.user;

import com.player32611.dto.CategoryListDTO;
import com.player32611.entity.Category;
import com.player32611.result.Result;
import com.player32611.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@Slf4j
@RequestMapping("/user/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result<List<Category>> list(CategoryListDTO categoryListDTO){
        log.info("条件查询分类请求: {}", categoryListDTO);

        List<Category> categoryList = categoryService.list(categoryListDTO);

        return Result.success(categoryList);
    }
}
