package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogComment;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);

    List<BlogComment> findBlogCommentList(Map map);

    int getTotalBlogComments(Map map);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
    
    int getTotalCommentByBlogIds(@Param("blogIds")List<Long> blogIds);
    
    List<BlogComment> getCommentListByBolgIds(@Param("blogIds")List<Long> blogIds);
}