package com.fish.web.action.blog;

import com.fish.biz.common.BaseErrResult;
import com.fish.biz.domain.blog.Blog;
import com.fish.biz.domain.blog.BlogLabel;
import com.fish.biz.domain.blog.Category;
import com.fish.biz.domain.blog.Label;
import com.fish.biz.domain.comment.Comment;
import com.fish.biz.enums.common.CommonStatus;
import com.fish.biz.service.blog.BlogService;
import com.fish.biz.vo.blog.BlogVO;
import com.fish.utils.FileUploadUtil;
import com.fish.web.action.common.BaseAction;
import com.fish.web.validator.blog.BlogValidator;
import com.fish.web.validator.blog.CommentValidator;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 博客主页
 * @author fish
 * @date 2016/5/19
 */

@Controller
public class BlogAction extends BaseAction{

    private static final Logger _log = Logger.getLogger(BlogAction.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogValidator blogValidator;


    /**
     * 博客列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/blog.htm")
    public String index(Model model){

        model.addAttribute("title", "fish的Blog");

        return "/blog/index";
    }

    /**
     * ajax异步加载博客列表，
     * @param blogVO
     * @param currentPage
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/blog/ajax_load.htm", method = RequestMethod.POST)
    public String  ajaxLoadData(@ModelAttribute("blogVO")BlogVO blogVO, Integer currentPage, Integer pageSize, Model model){

        if (currentPage == null){
            currentPage = 1;
        }

        if (pageSize == null){
            pageSize = 5;
        }

        PageInfo<Blog> page = blogService.pageQuery(blogVO,currentPage,pageSize);


        if (page.getSize() == 0){
            page = null;
        }

        model.addAttribute("page", page);

        return "/ajax/blog_list_ajax";
    }

    @RequestMapping(value = "/blog/details/{blogId}.htm", method = RequestMethod.GET)
    public String blogDetails(@PathVariable("blogId")Long blogId, Model model){

        model.addAttribute("blog", blogService.findByPrimaryKey(blogId));

        model.addAttribute("commentList", blogService.findComments(blogId));

        model.addAttribute("title", "我的博客");

        return "/blog/blog_detail";

    }

    @RequestMapping(value = "/blog/write_blog.htm", method = RequestMethod.GET)
    public String writeBlog(@ModelAttribute("blog")Blog blog, Model model){

        model.addAttribute("cateList", blogService.findCategorys(null));
        model.addAttribute("title", "写博客");
        return "/blog/write_blog";
    }

    @RequestMapping(value = "/blog/write_blog.htm", method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute("blog")Blog blog, BindingResult bindingResult,
                           @RequestParam(value = "titlePicImg", required = false)MultipartFile titlePicImg, Model model){

        blogValidator.validate(blog,bindingResult);

        if (bindingResult.hasErrors()){
            return "/blog/write_blog";
        }

        if (titlePicImg != null && titlePicImg.getSize() > 0){
            BaseErrResult errResult = FileUploadUtil.validateFile(titlePicImg, FileUploadUtil.FileType.IMAGE);
            if (errResult.isError()){
                model.addAttribute("imgError",errResult.getMessage());
                return "/blog/write_blog";
            }else{
                String storePath = "blog/pic/";
                try {
                    String uploadUrl = FileUploadUtil.uploadFile(storePath, titlePicImg);
                    blog.setTitlePic(uploadUrl);
                } catch (Exception e) {
                    _log.error("title picture upload error, for " + e.getMessage());
                    model.addAttribute("imgError",errResult.getMessage());
                    return "/blog/write_blog";
                }
            }
        }

        blog.setIsTop(0);
        blog.setReplyCount(0);
        blog.setStatus(CommonStatus.NORMAL.getCode());
        blog.setClickCount(0);
        blog.setGmtCreate(new Date());
        blog.setGmtModify(new Date());

        BaseErrResult result = blogService.saveBlog(blog);

        if (result.isError()){
            model.addAttribute("errMsg", result.getMessage());
            return "/blog/write_blog";
        }

        return "/blog/index";

    }

    @RequestMapping(value = "/blog/addComment.htm")
    @ResponseBody
    public String saveComment(@ModelAttribute("comment")Comment comment){

        BaseErrResult result = blogService.addComment(comment);

        if (result.isError()){
            return "N|" + result.getMessage();
        }

        return "Y|success";
    }

}
