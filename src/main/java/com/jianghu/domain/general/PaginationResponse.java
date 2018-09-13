package com.jianghu.domain.general;

import java.util.List;

public class PaginationResponse {
	private int total;// 总记录数
	private List<?> rows;// 符合条件的数据

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
