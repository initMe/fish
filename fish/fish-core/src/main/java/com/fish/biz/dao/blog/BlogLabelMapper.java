package com.fish.biz.dao.blog;

import com.fish.biz.domain.blog.BlogLabel;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogLabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogLabel record);

    int insertSelective(BlogLabel record);

    BlogLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogLabel record);

    int updateByPrimaryKey(BlogLabel record);
}