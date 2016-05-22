package com.fish.web.action.blog;

import com.fish.biz.domain.blog.Blog;
import com.fish.biz.service.blog.BlogService;
import com.fish.biz.vo.blog.BlogVO;
import com.fish.web.action.common.BaseAction;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("title", "我的博客");

        return "/blog/blog_detail";

    }

    @RequestMapping(value = "/blog/write_blog.htm")
    public String writeBlog(Model model){

        model.addAttribute("title", "写博客");
        return "/blog/write_blog";
    }

}
