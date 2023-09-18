package com.lyc.yl.dao;


import com.lyc.yl.entity.Address;

import java.util.List;

public interface AddressDao {

    void updateSelective(Address address);

    void insert(Address address);

    Address query(Address address);

    void update(Address address);

    void delete(Integer id);

    List<Address> queryList(Address address);
}
