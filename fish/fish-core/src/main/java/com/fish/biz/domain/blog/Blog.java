package com.fish.biz.domain.blog;

import java.util.Date;
import java.util.List;

public class Blog {
    /** blog主键 */
    private Long id;

    /** blog标题 */
    private String title;

    /** 所属分类 */
    private Long cateCode;

    /** 简介 */
    private String brief;

    /** 封面图片 */
    private String titlePic;

    /** 作者id */
    private Long authorId;

    /** 关键词 */
    private String keywords;

    /** 是否置顶 */
    private Integer isTop;

    /** 点击量 */
    private Integer clickCount;

    /** 评论数 */
    private Integer replyCount;

    /** 状态 */
    private Integer status;

    /** 创建者 */
    private String creator;

    /** 创建时间 */
    private Date gmtCreate;

    /** 修改者 */
    private String modifier;

    /** 修改时间 */
    private Date gmtModify;

    /** 内容 */
    private String content;

    /** 多个标签 id  **/
    private List<Label> labels;

    private String cateName;

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    /**
     * 评论数

     */
    private Integer commentCounts;

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getCateCode() {
        return cateCode;
    }

    public void setCateCode(Long cateCode) {
        this.cateCode = cateCode;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic == null ? null : titlePic.trim();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}