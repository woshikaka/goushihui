package com.sfmy.gsh.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 产品三级类型
 * @author 黄燕针
 */
@Entity
@Table(name = "product_third_type")
public class ProductThirdType implements Serializable{
	private static final long serialVersionUID = 3567426141262722678L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	public ProductThirdType(Integer id) {
		super();
		this.id = id;
	}

	public ProductThirdType() {
	}

	public ProductThirdType(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
