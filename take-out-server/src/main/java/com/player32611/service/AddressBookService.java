package com.player32611.service;

import com.player32611.dto.AddressBookDTO;
import com.player32611.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    void add(AddressBook addressBook);

    List<AddressBook> list();

    void setDefault(AddressBookDTO addressBookDTO);

    AddressBook id(Long id);

    void delete(AddressBookDTO addressBookDTO);

    void update(AddressBook addressBook);

    AddressBook getDefault();
}
