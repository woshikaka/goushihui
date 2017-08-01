package com.sfmy.gsh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.TextPlan;

public interface TextPlanDao extends JpaRepository<TextPlan,Integer>{

	TextPlan findByType(Integer type);

}