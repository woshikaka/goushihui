package com.sfmy.gsh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.entity.Ad;

public interface AdDao extends JpaRepository<Ad,Integer>{

	List<Ad> findAllByOrderByIsUseDesc();

//	Integer deleteById(List<Integer> adIds);

	List<Ad> findById(List<Integer> adIds);
	
	@Modifying 
	@Transactional
	@Query("update Ad set isUse = :isUse where id not in :ids")
	public void updateIsUseNotInIds(@Param("isUse")Boolean isUse,@Param("ids")List<Integer>ids);

	List<Ad> findByIsUse(boolean isUse); 
}