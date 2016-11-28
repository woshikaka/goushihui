package com.sfmy.gsh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.ProductType;

public interface ProductDao extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product>{
	public Page<Product> findAll(Specification<Product> specification, Pageable pageable);

	@Modifying
	@Query("update Product p set p.isShangJia = ?1 where p.id = ?2")
	public void setIsShangJiaById(@Param("isShangJia")boolean isShangJia,@Param("id")Integer id);

	@Modifying
	@Query("update Product p set p.isTop = ?1 where p.id in ?2")
	public void setIsTopByIds(@Param("isTop")boolean isTop,@Param("ids")List<Integer> ids);

	public Integer countByIsTopAndFirstType(boolean isTop, ProductType productType);

	public List<Product> findByIsTopAndFirstType(boolean isTop, ProductType productType);
}