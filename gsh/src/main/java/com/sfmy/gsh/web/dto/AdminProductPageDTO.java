package com.sfmy.gsh.web.dto;

import java.util.List;

import com.sfmy.gsh.web.base.PageDTO;

public class AdminProductPageDTO extends PageDTO{
	private List<AdminProductDTO> products;
	public List<AdminProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<AdminProductDTO> products) {
		this.products = products;
	}
}
