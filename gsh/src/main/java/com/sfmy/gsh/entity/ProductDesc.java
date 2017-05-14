package com.sfmy.gsh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 产品描述
 * @author 黄燕针
 */
@Entity
@Table(name = "product_desc")
public class ProductDesc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	@Column(name="html_desc")
	private String htmlDesc;

	public ProductDesc() {
	}

	public ProductDesc(Integer id, String htmlDesc) {
		super();
		this.id = id;
		this.htmlDesc = htmlDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHtmlDesc() {
		return htmlDesc;
	}

	public void setHtmlDesc(String htmlDesc) {
		this.htmlDesc = htmlDesc;
	}
}
