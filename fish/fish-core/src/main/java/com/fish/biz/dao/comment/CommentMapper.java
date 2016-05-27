package com.fish.biz.dao.comment;

import com.fish.biz.domain.comment.Comment;
import com.fish.biz.vo.blog.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    public List<Comment> selectComments(CommentVO commentVO);
}