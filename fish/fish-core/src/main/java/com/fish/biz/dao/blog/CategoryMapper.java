package com.fish.biz.dao.blog;

import com.fish.biz.domain.blog.Category;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    public List<Category> selectByQuery(Category category);

    @MapKey("id")
    public Map<Long, Category> selectMapByIds(ArrayList ids);
}