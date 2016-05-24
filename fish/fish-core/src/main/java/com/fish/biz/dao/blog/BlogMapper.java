package com.fish.biz.dao.blog;

import com.fish.biz.domain.blog.Blog;
import com.fish.biz.vo.blog.BlogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Blog record);

    Long insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    public List<Blog> selectByQuery(BlogVO query);
}