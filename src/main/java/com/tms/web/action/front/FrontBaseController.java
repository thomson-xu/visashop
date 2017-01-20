package com.tms.web.action.front;

import com.tms.core.Services;
import com.tms.core.dao.page.PagerModel;
import com.tms.core.front.SystemManager;
import com.tms.web.action.front.orders.CartInfo;
import com.tms.web.util.LoginUserHolder;
import com.tms.web.util.RequestHolder;
import com.tms.core.FrontContainer;
import com.tms.services.front.account.bean.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by dylan on 15-3-17.
 */
@Controller
public abstract class FrontBaseController<E extends PagerModel> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    public abstract Services<E> getService();
    protected static final String page_toLogin = "/account/login.html";
    protected static final String page_toLoginRedirect = "redirect:/account/login.html";

    @Autowired
    protected SystemManager systemManager;

    protected Account getLoginAccount(){
        return LoginUserHolder.getLoginAccount();
    }

    protected CartInfo getMyCart(){
        return (CartInfo) RequestHolder.getSession().getAttribute(FrontContainer.myCart);
    }


    /**
     * 查询分页信息列表
     */
    protected <X extends PagerModel> PagerModel selectPageList(Services<X> service, X model) throws Exception {
        int offset = 0;//分页偏移量
        if (RequestHolder.getRequest().getParameter("pager.offset") != null) {
            offset = Integer
                    .parseInt(RequestHolder.getRequest().getParameter("pager.offset"));
        }
        if (offset < 0)
            offset = 0;
        model.setOffset(offset);
        PagerModel pager = service.selectPageList(model);
        if (pager == null) {
            pager = new PagerModel();
        }
        // 计算总页数
        pager.setPagerSize((pager.getTotal() + pager.getPageSize() - 1)
                / pager.getPageSize());
        return pager;
    }

}
