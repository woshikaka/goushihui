package com.sfmy.gsh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable=false)
	private User user;
	/**
	 * 详细地址
	 */
	@Column(nullable=false,length=255)
	private String detailed;
	
	@Column(name="is_defaut",nullable=false)
	private Boolean isDefaut=false;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public Boolean getIsDefaut() {
		return isDefaut;
	}
	public void setIsDefaut(Boolean isDefaut) {
		this.isDefaut = isDefaut;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
