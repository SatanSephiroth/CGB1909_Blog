package com.site.blog.my.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 分页查询参数
 */
public class PageQueryUtil extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = -4932567676037987521L;
	// 当前页码
	private int page;
	// 每页条数
	private int limit;
	// 用户id
	private Integer userId;

	public PageQueryUtil(Map<String, Object> params) {
		this.putAll(params);
		// 接受用户id
		if (ShiroUtil.getLoginUser() == null) {
			this.put("userId", null);
		} else {
			this.put("userId", ShiroUtil.getUserId());
		}

		// 分页参数
		this.page = Integer.parseInt(params.get("page").toString());
		this.limit = Integer.parseInt(params.get("limit").toString());
		this.put("start", (page - 1) * limit);
		this.put("page", page);
		this.put("limit", limit);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "pageUtil [page=" + page + ", limit=" + limit + ", userId=" + userId + "]";
	}

}
