package com.sfmy.gsh.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.Order;
import com.sfmy.gsh.entity.User;

public interface OrderDao extends JpaRepository<Order,Integer>{
	Order findByOutTradeNo(String outTradeNo);
	Page<Order> findAll(Specification<Order> specification, Pageable pageable);
	Order findByOutTradeNoAndUser(String outTradeNo,User user);
}