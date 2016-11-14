package com.sfmy.gsh.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页参数
 * @author 黄燕针
 */
public class PageBean<E> {
	/**
	 * 本页的数据列表
	 */
	private List<E> recordList = new ArrayList<E>();
	/**
	 * 当前页码
	 */
	private int currPageNo=1;
	/**
	 * 每页显示多少条数据
	 */
	private int pageSize;
	/**
	 * 总记录数
	 */
	private int recordCount;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 页码列表的开始索引（从1开始）
	 */
	private int beginPageIndex;

	/**
	 * 页码列表的结束索引
	 */
	private int endPageIndex;
	
//	public PageBean() {
//	}

	public PageBean(List<E> recordList,int pageSize,int recordCount) {
		this.pageSize = pageSize;
		pageCount=(recordCount+pageSize-1)/pageSize;
//		this.recordCount = recordCount;
		setRecordCount(recordCount);
		this.recordList = recordList;
	}
	
	public List<E> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<E> recordList) {
		this.recordList = recordList;
	}
	public int getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		pageCount = (recordCount + pageSize - 1) / pageSize;
		if (pageCount <= 20) {// 总页数不多于20页，则全部显示
			beginPageIndex = 1;
			endPageIndex = pageCount;
		} else {
			// 当前页附近的共20个页码（前9个 + 当前页 + 后10个）
			beginPageIndex = currPageNo - 9;
			endPageIndex = currPageNo + 10;
			// 当前面的页码不足9个时，则显示前20个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 20;
			}
			// 当后面的页码不足10个时，则显示后20个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 20 + 1;
			}
		}
		
		if(currPageNo <= 0){
			currPageNo = 1;
		}else if(currPageNo >= pageCount && pageCount!=0){
			currPageNo = pageCount;
		}
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
}
