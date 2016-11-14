package com.sfmy.gsh.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.Product;

public interface ProductDao extends JpaRepository<Product,Integer>{
	public Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}