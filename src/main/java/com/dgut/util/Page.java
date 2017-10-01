package com.dgut.util;

/**
 * @ClassName: Page
 * @Description: 分页工具类
 * limit start, size;
 * @author 闲明苑
 * @date 2017年9月25日 上午9:40:18
 */
public class Page {

	private Integer start;// >=0
	private Integer size;
	private Integer currentpage;// 当前页 >= 1
	private Integer totalpage;// 总页数>=1

	/**
	 * @Title: Page
	 * @Description: 
	 * @param size 每页大小 >= 1
	 * @param currentpage 当前页 >= 1
	 * @param total 记录总数 >= 0
	 */
	public Page(Integer size, Integer currentpage, Integer total) throws RuntimeException {
		if (size < 1 || currentpage < 1 || total < 0) {
			throw new RuntimeException("分页参数错误");
		}
		this.start = (currentpage - 1) * size;
		this.size = size;
		this.currentpage = currentpage;
		// total == 0 则总页数为1，否则为 total / size + (total % size > 0 ? 1 : 0)
		this.totalpage = total == 0 ? 1 : total / size + (total % size > 0 ? 1 : 0);
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}

	public Integer getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}

	@Override
	public String toString() {
		return "Page [start=" + start + ", size=" + size + ", currentpage=" + currentpage + ", totalpage=" + totalpage
				+ "]";
	}

}
