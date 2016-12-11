package com.sfmy.gsh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.Address;
import com.sfmy.gsh.entity.User;

public interface AddressDao extends JpaRepository<Address,Integer>{

	Integer countByUser(User user);

	List<Address> findAllByUser(User user);
}