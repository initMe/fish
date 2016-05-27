package com.fish.biz.dao.blog;

import com.fish.biz.domain.blog.BlogLabel;
import com.fish.biz.domain.blog.Label;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogLabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogLabel record);

    int insertSelective(BlogLabel record);

    BlogLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogLabel record);

    int updateByPrimaryKey(BlogLabel record);
}