package com.sfmy.gsh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.Product;

public interface ProductDao extends JpaRepository<Product,Integer>{

}