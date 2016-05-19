package com.fish.biz.domain.blog;

public class BlogCategory {
    /** 主键 */
    private Long id;

    /** 文章id */
    private Long blogId;

    /** 分类id */
    private Long cateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }
}