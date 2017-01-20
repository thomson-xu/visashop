package com.tms.core.freemarker.fn;

import com.tms.web.util.LoginUserHolder;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * 获取当前登录的用户(前端用户)
 * @author dylan
 */
public class CurrentAccountGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return LoginUserHolder.getLoginAccount();
    }
}
