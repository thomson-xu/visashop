package com.tms.core.freemarker.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import com.tms.web.util.LoginUserHolder;

import java.util.List;

/**
 * 获取当前登录的用户
 * Created by dylan on 15-1-15.
 */
public class CurrentUserGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return LoginUserHolder.getLoginUser();
    }
}
