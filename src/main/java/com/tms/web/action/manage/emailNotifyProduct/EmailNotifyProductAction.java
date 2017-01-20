package com.tms.web.action.manage.emailNotifyProduct;import com.tms.services.manage.emailNotifyProduct.EmailNotifyProductService;import com.tms.services.manage.emailNotifyProduct.bean.EmailNotifyProduct;import com.tms.web.action.BaseController;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import java.io.IOException;/** * 到货通知 */@Controller@RequestMapping("/manage/emailNotifyProduct/")public class EmailNotifyProductAction extends BaseController<EmailNotifyProduct> {	private static final long serialVersionUID = 1L;	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailNotifyProductAction.class);	@Autowired	private EmailNotifyProductService emailNotifyProductService;	private static final String page_toList = "/manage/emailNotifyProduct/emailNotifyProductList";	private EmailNotifyProductAction() {		super.page_toList = page_toList;		super.page_toAdd = null;		super.page_toEdit = null;	}	@Override	public EmailNotifyProductService getService() {		return emailNotifyProductService;	}	public void setEmailNotifyProductService(			EmailNotifyProductService emailNotifyProductService) {		this.emailNotifyProductService = emailNotifyProductService;	}	@RequestMapping("autoNotify")	@ResponseBody	public String autoNotify(EmailNotifyProduct e) throws IOException{		logger.error("autoNotify...");		emailNotifyProductService.autoNotify();		return "success";	}}