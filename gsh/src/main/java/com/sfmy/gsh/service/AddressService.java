package com.sfmy.gsh.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
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
		if (address.getIsDefaut()) {
			List<Address> all = addressDao.findAllByUser(address.getUser());
			if (CollectionUtils.isNotEmpty(all)) {
				for (Address temp : all) {
					temp.setIsDefaut(false);
					addressDao.save(temp);
				}
			}
		}
		addressDao.save(address);
	}

	public Integer countAddress(Integer currUserId) {
		return addressDao.countByUser(new User(currUserId));
	}

	public List<Address> findAllAddress(Integer currUserId) {
		return addressDao.findAllByUserOrderByIsDefautDesc(new User(currUserId));
	}

	public void deleteAddress(User user, Integer id) {
		addressDao.deleteByUserAndId(user, id);
	}

	public void setDefaut(User user, Integer id) {
		List<Address> all = addressDao.findAllByUser(user);
		if (CollectionUtils.isNotEmpty(all)) {
			for (Address temp : all) {
				if(temp.getId().intValue() == id.intValue()){
					temp.setIsDefaut(true);
				}else{
					temp.setIsDefaut(false);
				}
				addressDao.save(temp);
			}
		}
	}

	public Address findAddress(Integer currUserId, Integer addressId) {
		return addressDao.findByUserAndId(new User(currUserId),addressId);
	}
}
