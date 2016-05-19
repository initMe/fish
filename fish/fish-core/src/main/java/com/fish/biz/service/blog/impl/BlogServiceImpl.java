package com.fish.biz.service.blog.impl;

import com.fish.biz.dao.blog.BlogMapper;
import com.fish.biz.domain.blog.Blog;
import com.fish.biz.service.blog.BlogService;
import com.fish.biz.vo.blog.BlogVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author fish
 * @date 2016/5/19
 */

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageInfo<Blog> pageQuery(BlogVO query, Integer currentPage, Integer pageSize) {

        Assert.notNull(query, "blogVO");

        PageHelper.startPage(currentPage,pageSize);
        List<Blog> blogList = blogMapper.selectByQuery(query);

        return new PageInfo<Blog>(blogList);
    }
}
