package com.sfmy.gsh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sfmy.gsh.entity.CarProduct;
import com.sfmy.gsh.entity.User;

public interface CarProductDao extends JpaRepository<CarProduct,Integer>{

	CarProduct findByProductIdAndUserId(Integer productId, Integer userId);

	List<CarProduct> findByUser(User user);

	@Query("select c.count from CarProduct c where user = ?1")
	List<Integer> findProductCntByUser(User user);

	Integer deleteByUserAndId(User user, Integer id);

}