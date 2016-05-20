package com.fish.biz.service.blog;

import com.fish.biz.domain.blog.Blog;
import com.fish.biz.vo.blog.BlogVO;
import com.github.pagehelper.PageInfo;

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

}
