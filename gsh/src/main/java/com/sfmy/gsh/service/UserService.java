package com.sfmy.gsh.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
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
	 * 
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
	
	public Boolean inputPasswordEqualsSourcePassword(Integer userId,String password) {
		if(StringUtils.isBlank(password)){
			return false;
		}
		
		User user = userDao.findOne(userId);
		
		String hashedPassword = new Md5Hash(password,user.getSalt(),1024).toString();
		return StringUtils.equals(user.getPassword(),hashedPassword);
	}
	
	public void modifyPassword(Integer userId,String newPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		ByteSource byteSource = rng.nextBytes();
		String salt = byteSource.toHex();//随机盐
		
		String hashedPassword = new Md5Hash(newPassword,salt,1024).toString();
		
		User user = userDao.findOne(userId);
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		userDao.save(user);
	}
}
