package com.common.model;

import com.common.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

	private int offset = 0;
	private int limit = 10;
	private String order = Constants.DEFAULT_ORDER;
	private String sort = Constants.DEFAULT_SORT;
	private long fromDate = 0;
	private long toDate = 0;
	private String entityState = Constants.ALL;
	private String entityType = Constants.ALL;

}
