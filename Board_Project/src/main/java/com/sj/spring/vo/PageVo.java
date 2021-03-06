package com.sj.spring.vo;

import lombok.Getter;

@Getter
public class PageVo {
	
	private int min;
	private int max;
	
	private int prevPage;
	private int nextPage;
	
	private int pageCnt;
	private int currentPage;
	
	public PageVo(int contentCnt, int page, int page_contentCnt, int page_buttonCnt) {
		
		this.currentPage = page;
		
		pageCnt = contentCnt / page_contentCnt;
		if(contentCnt % page_contentCnt > 0) {
			pageCnt += 1;
		}
		if(pageCnt == 0) {
			pageCnt = 1;
		}
		
		min = (currentPage - 1) / page_buttonCnt * page_buttonCnt + 1;
		max = min + page_buttonCnt - 1;
		
		if(max > pageCnt) {
			max = pageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
		
		if(prevPage < 1) {
			prevPage = 1;
		}
		
	}
}