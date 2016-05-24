package com.fish.web.validator.blog;

import com.fish.biz.domain.blog.Blog;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * blog验证器
 * @author fish
 * @date 2016/5/23
 */
@Component("blogValidator")
public class BlogValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return this.getClass().equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Blog blog = (Blog)o;

        if (StringUtils.isBlank(blog.getTitle())){
            errors.rejectValue("title", null, null, "博客标题不能为空");
        }



    }
}
