package com.jianghu.domain.general;


public class PaginationRequest {
	private int page;// 页码
	private int rows;// 每页多少行

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
