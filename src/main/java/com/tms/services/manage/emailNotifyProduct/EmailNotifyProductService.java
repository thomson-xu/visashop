package com.tms.services.manage.emailNotifyProduct;import com.tms.core.Services;import com.tms.services.manage.emailNotifyProduct.bean.EmailNotifyProduct;public interface EmailNotifyProductService extends Services<EmailNotifyProduct> {	/**	 * 系统自动发送到货通知	 */	void autoNotify();}