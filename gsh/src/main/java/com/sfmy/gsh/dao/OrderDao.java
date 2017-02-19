package com.sfmy.gsh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.Order;

public interface OrderDao extends JpaRepository<Order,Integer>{

	Order findByOutTradeNo(String outTradeNo);
}