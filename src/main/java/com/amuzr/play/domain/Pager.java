package com.amuzr.play.domain;

public class Pager {
	public final int BlogPerPage = 5; // 한페이지에 보이는 블로그 수
	public final int PagePerBlock = 5; // 보이는 페이지 수
	
	private int curPage;
	private int results;
	private int startPage;
	private int maxPage;
	private int prevPage;
	private int nextPage;
	
	public int getBlogPerPage() {
		return BlogPerPage;
	}
	public int getPagePerBlock() {
		return PagePerBlock;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void init() {
		int curBlock = oddDiv(curPage,PagePerBlock);
		this.startPage = (curBlock-1)*PagePerBlock+1;
		this.maxPage = oddDiv(results,BlogPerPage);
		this.prevPage = (curPage == 1) ? 0 : curPage-1;
		this.nextPage = (curPage == maxPage) ? 0 : curPage+1;
	}
	
	public int oddDiv(int a, int b) {
		return ((a % b) == 0 && a!=0) ? (a/b) : (a/b+1);
	}
	
}
