package com.sfmy.gsh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.ProductType;

public interface ProductTypeDao extends JpaRepository<ProductType,Integer>{

	List<ProductType> findAll();

}