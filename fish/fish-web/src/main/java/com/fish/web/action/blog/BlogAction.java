package com.fish.web.action.blog;

import com.fish.biz.domain.blog.Blog;
import com.fish.biz.service.blog.BlogService;
import com.fish.biz.vo.blog.BlogVO;
import com.fish.web.action.common.BaseAction;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博客主页
 * @author fish
 * @date 2016/5/19
 */

@Controller
public class BlogAction extends BaseAction{

    //private static final Logger _log = Logger.getLogger(BlogAction.class);

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/blog.htm")
    public String index(@ModelAttribute("blogVO")BlogVO blogVO, Model model, Integer currentPage, Integer pageSize){

        if (currentPage == null){
            currentPage = 1;
        }

        if (pageSize == null){
            pageSize = 10;
        }

        PageInfo<Blog> page = blogService.pageQuery(blogVO,currentPage,pageSize);

        model.addAttribute("title", "fish的Blog");
        model.addAttribute("page", page);

        return "/blog/index";
    }

}
