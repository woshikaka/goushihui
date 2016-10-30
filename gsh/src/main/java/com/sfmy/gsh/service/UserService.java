package com.sfmy.gsh.service;

import javax.annotation.Resource;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.UserDao;
import com.sfmy.gsh.entity.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	@Resource
	private UserDao userDao;
	/**
	 * admin  sfmy66admin88
	 */
	public void saveUser(User u) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		ByteSource byteSource = rng.nextBytes();
		String salt = byteSource.toHex();//随机盐
		
//		String hashedPassword = new Sha256Hash(u.getPassword(),salt,1024).toString();
		String hashedPassword = new Md5Hash(u.getPassword(),salt,1024).toString();
		u.setPassword(hashedPassword);
		u.setSalt(salt);
		userDao.save(u);
	}

	public void deleteUser(User u) {
		userDao.delete(u);
	}

	public void updateUser(User u) {
		userDao.save(u);
	}

	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}
}
