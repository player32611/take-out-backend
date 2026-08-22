package com.player32611.controller.admin;

import com.player32611.dto.CategoryDTO;
import com.player32611.dto.CategoryDeleteDTO;
import com.player32611.dto.CategoryListDTO;
import com.player32611.dto.CategoryPageDTO;
import com.player32611.entity.Category;
import com.player32611.result.PageResult;
import com.player32611.result.Result;
import com.player32611.service.CategoryService;
import com.player32611.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult<Category>> page(CategoryPageDTO categoryPageDTO){
        log.info("分类分页查询请求: {}", categoryPageDTO);

        PageResult<Category> pageResult = categoryService.page(categoryPageDTO);

        return Result.success(pageResult);
    }

    @PostMapping
    public Result<CategoryVO> add(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类请求: {}", categoryDTO);

        categoryService.add(categoryDTO);

        return Result.success();
    }

    @PutMapping
    public Result<CategoryVO> update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类请求: {}", categoryDTO);

        categoryService.update(categoryDTO);

        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result<CategoryVO> status(@PathVariable Integer status, Long id){
        log.info("启用、禁用分类请求: {}, {}", status, id);

        categoryService.status(status, id);

        return Result.success();
    }

    @DeleteMapping
    public Result<CategoryVO> delete(CategoryDeleteDTO categoryDeleteDTO){
        log.info("删除分类请求: {}", categoryDeleteDTO);

        categoryService.delete(categoryDeleteDTO);

        return Result.success();
    }
    @GetMapping("/list")
    public Result<List<Category>> list(CategoryListDTO categoryListDTO){
        log.info("根据类型查询分类请求: {}", categoryListDTO);

        List<Category> categoryList = categoryService.list(categoryListDTO);

        return Result.success(categoryList);
    }
}
