package cn.taskoa.common.utils;

import java.util.List;

public class PageModel<E> {
	private int pageSize = 15; // 每页显示记录数
	private int totalRecords;// 记录总个数
	private int currentPage; // 当前页
	private List<E> pageList; // 返回的记录

	public int getPageSize() {
		return pageSize;
	}

	// 总页数
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<E> getPageList() {
		return pageList;
	}

	public void setPageList(List<E> pageList) {
		this.pageList = pageList;
	}
}
