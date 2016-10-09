package com.sfmy.gsh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sfmy.gsh.entity.User;

public interface UserDao extends JpaRepository<User,Integer>{

	User findUserByName(String name);

}