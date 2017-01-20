package com.tms.core.freemarker.fn;

import com.tms.web.util.RequestHolder;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import com.tms.core.FrontContainer;

import java.util.List;

/**
 * 获取购物车
 * @author dylan
 */
public class ShoppingCartGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return RequestHolder.getSession().getAttribute(FrontContainer.myCart);
    }
}
