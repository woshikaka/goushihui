package com.sfmy.gsh.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
/**
 * 产品二级类型
 * @author 黄燕针
 */
@Entity
@Table(name = "product_sec_type")
public class ProductSecType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
//	@Column(name = "first_type")
//	@Enumerated(EnumType.STRING)
//	private ProductFirstType firstType;
	
	@OneToMany(cascade={CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
			name="product_sec_type_product_third_type",
			joinColumns = {@JoinColumn(name = "product_sec_id", referencedColumnName = "id")},
			inverseJoinColumns={@JoinColumn(name = "product_third_id", referencedColumnName = "id")}
			)
	private List<ProductThirdType> thirdTypes;

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

	public List<ProductThirdType> getThirdTypes() {
		return thirdTypes;
	}

	public void setThirdTypes(List<ProductThirdType> thirdTypes) {
		this.thirdTypes = thirdTypes;
	}
}
