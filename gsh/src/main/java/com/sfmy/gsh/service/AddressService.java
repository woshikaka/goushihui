package com.sfmy.gsh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.AddressDao;
import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.entity.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddressService {
	@Resource
	private AddressDao addressDao;

	public void saveAddress(Address address) {
		addressDao.save(address);
	}

	public Integer countAddress(Integer currUserId) {
		return addressDao.countByUser(new User(currUserId));
	}

	public List<Address> findAllAddress(Integer currUserId) {
		return addressDao.findAllByUser(new User(currUserId));
	}
}
