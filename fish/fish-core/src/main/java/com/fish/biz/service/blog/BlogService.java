package com.fish.biz.service.blog;

import com.fish.biz.common.BaseErrResult;
import com.fish.biz.domain.blog.Blog;
import com.fish.biz.domain.blog.BlogLabel;
import com.fish.biz.domain.blog.Category;
import com.fish.biz.domain.blog.Label;
import com.fish.biz.domain.comment.Comment;
import com.fish.biz.vo.blog.BlogVO;
import com.github.pagehelper.PageInfo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fish
 * @date 2016/5/19
 */
public interface BlogService {

    /**
     * 分页查询博客
     * @param query
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<Blog> pageQuery(BlogVO query, Integer currentPage, Integer pageSize);

    public Blog findByPrimaryKey(Long blogId);

    public BaseErrResult saveBlog(Blog blog);

    public List<Category> findCategorys(Category category);

    public BaseErrResult addComment(Comment comment);

    public List<Comment> findComments(Long blogId);
}
