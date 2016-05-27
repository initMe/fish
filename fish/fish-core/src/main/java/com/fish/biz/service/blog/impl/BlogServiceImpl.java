package com.fish.biz.service.blog.impl;

import com.fish.biz.common.BaseErrResult;
import com.fish.biz.dao.blog.*;
import com.fish.biz.dao.comment.CommentMapper;
import com.fish.biz.dao.user.UserMapper;
import com.fish.biz.domain.blog.*;
import com.fish.biz.domain.comment.Comment;
import com.fish.biz.domain.user.User;
import com.fish.biz.enums.common.CommonStatus;
import com.fish.biz.service.blog.BlogService;
import com.fish.biz.vo.blog.BlogVO;
import com.fish.biz.vo.blog.CommentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CommentMapper commentMapper;

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

//        if (CollectionUtils.isNotEmpty(blog.getLabels())){
//            BlogLabel label = new BlogLabel();
//            label.setBlogId(blogId);
//            for (Long labelId : blog.getLabels()){
//                label.setLabelId(labelId);
//                blogLabelMapper.insert(label);
//            }
//        }

        return result;
    }

    @Override
    public List<Category> findCategorys(Category category) {
        return categoryMapper.selectByQuery(category);
    }

    @Override
    @Transactional
    public BaseErrResult addComment(Comment comment) {
        BaseErrResult result = new BaseErrResult();
        if (comment.getBlogId() == null){
            result.setMessage("对应的博客不能为空");
            return result;
        }

        User user = userMapper.selectByPrimaryKey(comment.getUserId());
        if (comment.getUserId() == null || user == null){
            result.setMessage("评论人不能为空");
            return result;
        }

        if (StringUtils.isBlank(comment.getContent())){
            result.setMessage("评论内容不能为空");
            return result;
        }

        comment.setStatus(CommonStatus.NORMAL.getCode());
        comment.setAgreeCount(0);
        comment.setGmtCreate(new Date());
        comment.setGmtModify(new Date());
        comment.setCreator(user.getAccount());

        commentMapper.insertSelective(comment);

        Blog blog = blogMapper.selectByPrimaryKey(comment.getBlogId());

        blog.setReplyCount(blog.getReplyCount() + 1);

        blogMapper.updateByPrimaryKeySelective(blog);

        return result;
    }

    @Override
    public List<Comment> findComments(Long blogId) {

        Assert.notNull(blogId, "blogId");

        CommentVO commentVO = new CommentVO();
        commentVO.setBlogId(blogId);

        return commentMapper.selectComments(commentVO);
    }

}
