package com.sfmy.gsh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.UserDao;
import com.sfmy.gsh.entity.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	@Resource
	private UserDao userDao;

	public void saveUser(User u) {
		userDao.save(u);
	}

	public void deleteUser(User u) {
		userDao.delete(u);
	}

	public void updateUser(User u) {
		userDao.save(u);
	}
}
