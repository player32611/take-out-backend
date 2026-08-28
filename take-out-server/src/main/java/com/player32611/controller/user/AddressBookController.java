package com.player32611.controller.user;

import com.player32611.dto.AddressBookDTO;
import com.player32611.entity.AddressBook;
import com.player32611.result.Result;
import com.player32611.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Slf4j
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping
    public Result add(@RequestBody AddressBook addressBook){
        log.info("新增地址请求: {}", addressBook);

        addressBookService.add(addressBook);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<AddressBook>> list(){
        log.info("查询当前登录用户的所有地址信息请求");

        List<AddressBook> addressBookList = addressBookService.list();

        return Result.success(addressBookList);
    }

    @PutMapping("/default")
    public Result setDefault(@RequestBody AddressBookDTO addressBookDTO){
        log.info("设置默认地址请求: {}", addressBookDTO);

        addressBookService.setDefault(addressBookDTO);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<AddressBook> id(@PathVariable Long id){
        log.info("根据id查询地址: {}", id);

        AddressBook addressBook = addressBookService.id(id);

        return Result.success(addressBook);
    }

    @DeleteMapping
    public Result delete(AddressBookDTO addressBookDTO){
        log.info("根据id删除地址请求: {}", addressBookDTO);

        addressBookService.delete(addressBookDTO);

        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody AddressBook addressBook){
        log.info("根据id修改地址: {}", addressBook);

        addressBookService.update(addressBook);

        return Result.success();
    }
}
