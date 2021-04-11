package cn.wft.common.utils;

import lombok.Data;

import java.util.List;

/**
 * 
 * @author zyh
 * @Title: PagedGridResult.java
 * @Package com.imooc.utils
 * @Description: 用来返回分页Grid的数据格式
 * Copyright: Copyright (c) 2019
 */
@Data
public class PagedGridResult {

	private int page;
	private long total;
	private long records;
	private List<?> rows;


}
