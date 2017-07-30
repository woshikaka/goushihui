package com.sfmy.gsh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "product_type")
public class ProductType implements Serializable{
	private static final long serialVersionUID = 3897602541246594199L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name",nullable=false)
	private String name;
	
	@Column(name = "`index`",nullable=false)
	private Integer index;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
//	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
			name="product_type_product_sec_type",
			joinColumns = {@JoinColumn(name = "product_type_id", referencedColumnName = "id")},
			inverseJoinColumns={@JoinColumn(name = "product_sec_id", referencedColumnName = "id")}
			)
	private List<ProductSecType> productSecTypes = new ArrayList<ProductSecType>();

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public ProductType() {
		super();
	}

	public ProductType(Integer id) {
		super();
		this.id = id;
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

	public List<ProductSecType> getProductSecTypes() {
		return productSecTypes;
	}

	public void setProductSecTypes(List<ProductSecType> productSecTypes) {
		this.productSecTypes = productSecTypes;
	}
}
