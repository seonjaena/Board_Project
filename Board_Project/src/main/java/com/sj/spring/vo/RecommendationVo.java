package com.sj.spring.vo;

import lombok.Data;

@Data
public class RecommendationVo {

	private int recommendation_idx;
	private int board_idx;
	private int user_idx;
	private int is_delete;
	
}
