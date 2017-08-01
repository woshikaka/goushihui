package com.sfmy.gsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.TextPlanDao;
import com.sfmy.gsh.entity.TextPlan;

@Service
@Transactional(rollbackFor = Exception.class)
public class TextPlanService {
	@Autowired
	private TextPlanDao textPlanDao;

	public TextPlan findByType(Integer typeId) {
		return textPlanDao.findByType(typeId);
	}

	public void save(Integer type, String ruleContent) {
		TextPlan textPlan = textPlanDao.findByType(type);
		textPlan.setContent(ruleContent);
		textPlanDao.save(textPlan);
	}
}
