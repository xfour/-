package com.xfour.util;

/*
 * 用于实现分页操作的工具类
 */
public class PageUtil {
	
	private int start;//指向每个分页的第一条数据
	private int count;//一次分页显示的数据条数
	private int total;//总数据条数
	private String params;//如属性管理等需要多传一个参数cid=?;通过此字符串来设置
	
	public PageUtil() {
		
	}

	public PageUtil(int start, int count) {
		this.start = start;
		this.count = count;
	}

	public int getStart() {
		return start;
	}

	public int getCount() {
		return count;
	}

	public int getTotal() {
		return total;
	}
	public String getParams() {
		return params;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setParams(String params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "PageUtil [start=" + start + ", count=" + count + ", total=" + total + "]";
	}
	
	//是否有前一页
	public boolean isHasPrevious() {
		if(start==0) return false;//如果start已经是第一页的下标值
		return true;
	}
	
	//是否有后一页
	public boolean isHasNext() {
		if(start==getLastPageStart()) return false;//如果start已经是最后一页的下标值
		return true;
	}
	
	//获取总页数
	public int getTotalPage() {
		if(total<=0) return 1;
		else if(total%count == 0) return total/count;
		else return total/count+1;
	}
	
	//获取最后那页第一条数据的下标
	public int getLastPageStart() {
		if(total<=count) return 0;
		else if(total%count==0) return total-count;
		else return total-total%count;
	}
}
