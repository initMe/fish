package com.fish.biz.service.blog.impl;

import com.fish.biz.common.BaseErrResult;
import com.fish.biz.dao.blog.BlogCategoryMapper;
import com.fish.biz.dao.blog.BlogLabelMapper;
import com.fish.biz.dao.blog.BlogMapper;
import com.fish.biz.dao.blog.CategoryMapper;
import com.fish.biz.dao.user.UserMapper;
import com.fish.biz.domain.blog.Blog;
import com.fish.biz.domain.blog.BlogCategory;
import com.fish.biz.domain.blog.BlogLabel;
import com.fish.biz.domain.blog.Category;
import com.fish.biz.domain.user.User;
import com.fish.biz.service.blog.BlogService;
import com.fish.biz.vo.blog.BlogVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogLabelMapper blogLabelMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Blog> pageQuery(BlogVO query, Integer currentPage, Integer pageSize) {

        Assert.notNull(query, "blogVO");

        PageHelper.startPage(currentPage,pageSize);
        List<Blog> blogList = blogMapper.selectByQuery(query);

        return new PageInfo<Blog>(blogList);
    }

    @Override
    public Blog findByPrimaryKey(Long blogId) {

        Assert.notNull(blogId, "blogId");

        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Transactional
    @Override
    public BaseErrResult saveBlog(Blog blog) {
        BaseErrResult result = new BaseErrResult();

        Long authorId = blog.getAuthorId();

        User user = userMapper.selectByPrimaryKey(authorId);

        if (user == null){
            result.setMessage("blog作者为空");
            return result;
        }else {
            blog.setCreator(user.getAccount());
            blog.setModifier(user.getAccount());
        }

        Long blogId = blogMapper.insertSelective(blog);

        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setBlogId(blogId);
        blogCategory.setCateId(blog.getCateCode());
        blogCategoryMapper.insert(blogCategory);

        if (CollectionUtils.isNotEmpty(blog.getLabels())){
            BlogLabel label = new BlogLabel();
            label.setBlogId(blogId);
            for (Long labelId : blog.getLabels()){
                label.setLabelId(labelId);
                blogLabelMapper.insert(label);
            }
        }

        return result;
    }

    @Override
    public List<Category> findCategorys(Category category) {
        return categoryMapper.selectByQuery(category);
    }
}
