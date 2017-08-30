package com.sfmy.gsh.bean;

import java.util.List;

public class PageBean1<E> {
	private List<E> content;
	private int totalPages;
	private int number;
	private long totalElements;
	public List<E> getContent() {
		return content;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public void setContent(List<E> content) {
		this.content = content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
