package com.player32611.service.impl;

import com.player32611.constant.StatusConstant;
import com.player32611.context.BaseContext;
import com.player32611.dto.AddressBookDTO;
import com.player32611.entity.AddressBook;
import com.player32611.mapper.AddressBookMapper;
import com.player32611.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public void add(AddressBook addressBook){
        List<AddressBook> addressBookList = addressBookMapper.selectByUserId(BaseContext.getCurrentId());

        if(addressBookList != null && !addressBookList.isEmpty()) addressBook.setIsDefault(StatusConstant.DISABLE);
        else addressBook.setIsDefault(StatusConstant.ENABLE);

        addressBook.setUserId(BaseContext.getCurrentId());

        addressBookMapper.insert(addressBook);
    }

    @Override
    public List<AddressBook> list(){
        return addressBookMapper.selectByUserId(BaseContext.getCurrentId());
    }

    @Override
    public void setDefault(AddressBookDTO addressBookDTO){

        addressBookMapper.updateIsDefaultByUserId(BaseContext.getCurrentId(), StatusConstant.DISABLE);

        AddressBook addressBook = AddressBook.builder()
                .id(addressBookDTO.getId())
                .isDefault(StatusConstant.ENABLE)
                .build();
        addressBookMapper.update(addressBook);
    }

    @Override
    public AddressBook id(Long id){
        return addressBookMapper.selectById(id);
    }

    @Override
    public void delete(AddressBookDTO addressBookDTO){
        addressBookMapper.deleteById(addressBookDTO.getId());
    }

    @Override
    public void update(AddressBook addressBook){
        addressBookMapper.update(addressBook);
    }
}
