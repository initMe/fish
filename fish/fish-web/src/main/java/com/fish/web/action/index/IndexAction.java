package com.fish.web.action.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fish
 * @date 2016/5/12
 */
@Controller
public class IndexAction {

    @RequestMapping(value = "/index.htm")
    public String index(Model model){

        return "/index";
    }
}
