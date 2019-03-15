package com.want.mq.model;

import java.util.List;

/****************************   
* http://i.want-want.com
*
* @Description: 
* @version: v1.0.0
* @author: 00291315
* @date: 2019年1月28日 上午10:22:03 
* Modification History: 
* 1.  00291315  2019年1月28日  初始创建
*******************************/
public class Paging<T> {
	
	//总页数
    private Long totalPage;

    //总记录数
    private Long totalCount;

    //每页显示集合
    private List<T> rows;

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
