package com.lyc.yl.service;

import com.lyc.yl.dao.AddressDao;
import com.lyc.yl.entity.Address;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService {
    @Resource
    private AddressDao addressMapper;

    public Address query(Address address) {
        return addressMapper.query(address);
    }

    public void add(Address address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        addressMapper.insert(address);
    }

    public void update(Address address) {
        address.setUpdateTime(LocalDateTime.now());
        addressMapper.update(address);
    }

    public void delete(Integer id) {
        addressMapper.delete(id);
    }

    public void resetDefault(Integer userId) {
        Address address = new Address();
        address.setIsDefault(0);
        address.setUserId(userId);
        address.setUpdateTime(LocalDateTime.now());
        addressMapper.updateSelective(address);
    }

    public List<Address> queryList(Address address) {
        return addressMapper.queryList(address);
    }
}
