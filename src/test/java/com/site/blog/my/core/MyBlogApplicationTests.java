package com.site.blog.my.core;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.site.blog.my.core.dao.AdminUserMapper;
import com.site.blog.my.core.dao.BlogCommentMapper;
import com.site.blog.my.core.dao.BlogMapper;
import com.site.blog.my.core.entity.BlogComment;
import com.site.blog.my.core.service.AdminUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private BlogCommentMapper blogCommentMapper;

	@Test
	public void testInsertDao() {
		int row = adminUserMapper.insert("王海涛", "654321", "涛哥");
		System.out.println("影响的行数:" + row);
	}

	@Test
	public void testInsertService() {
		int row = adminUserService.insert("王海涛", "654321", "654321", "涛哥");
		System.out.println("影响的行数:" + row);
	}
	@Test
	public void testFindBlogIds() {
		List<Long> blogIds = blogMapper.findBlogIdsByUserId(1);
		List<BlogComment> total = blogCommentMapper.getCommentListByBolgIds(blogIds);
		System.out.println("评论总数:"+total);
	}
	@Test
	public void testFindBlogIdsByUserId() {
		List<Long> blogIds = blogMapper.findBlogIdsByUserId(1);
		List<BlogComment> list = blogCommentMapper.getCommentListByBolgIds(blogIds);
		for (BlogComment b : list) {
			System.out.println(b);
		}
		System.out.println(list.size());
	}
}
