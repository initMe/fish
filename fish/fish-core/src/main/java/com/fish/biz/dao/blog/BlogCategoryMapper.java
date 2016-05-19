package com.fish.biz.dao.blog;

import com.fish.biz.domain.blog.BlogCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogCategory record);

    int insertSelective(BlogCategory record);

    BlogCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogCategory record);

    int updateByPrimaryKey(BlogCategory record);
}