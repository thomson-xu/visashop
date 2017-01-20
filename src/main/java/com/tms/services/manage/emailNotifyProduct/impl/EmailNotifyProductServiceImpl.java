package com.tms.services.manage.emailNotifyProduct.impl;import java.util.HashMap;import java.util.LinkedList;import java.util.List;import java.util.Map;import com.tms.core.ServersManager;import com.tms.core.util.MailUtil;import com.tms.services.manage.account.AccountService;import com.tms.services.manage.notifyTemplate.bean.NotifyTemplate;import com.tms.services.manage.systemSetting.bean.SystemSetting;import org.apache.commons.lang.StringUtils;import org.slf4j.LoggerFactory;import com.tms.core.util.FreemarkerTemplateUtil;import com.tms.services.manage.account.bean.Account;import com.tms.services.manage.emailNotifyProduct.EmailNotifyProductService;import com.tms.services.manage.emailNotifyProduct.bean.EmailNotifyProduct;import com.tms.services.manage.emailNotifyProduct.dao.EmailNotifyProductDao;import com.tms.services.manage.product.ProductService;import com.tms.services.manage.product.bean.Product;import org.springframework.stereotype.Service;import javax.annotation.Resource;/** * 商品到货系统邮件通知 * @author jqsl2012@163.com * */@Service("emailNotifyProductManage")public class EmailNotifyProductServiceImpl extends		ServersManager<EmailNotifyProduct, EmailNotifyProductDao> implements EmailNotifyProductService {		private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailNotifyProductServiceImpl.class);    @Resource(name = "productServiceManage")	private ProductService productService;    @Resource(name = "accountServiceManage")	private AccountService accountService;    @Resource(name = "emailNotifyProductDaoManage")    @Override    public void setDao(EmailNotifyProductDao emailNotifyProductDao) {        this.dao = emailNotifyProductDao;    }    public void setAccountService(AccountService accountService) {		this.accountService = accountService;	}	public void setProductService(ProductService productService) {		this.productService = productService;	}	@Override	public void autoNotify() {		logger.error("autoNotify...");				//查询所有需要通知的列表		EmailNotifyProduct info = new EmailNotifyProduct();		info.setStatus(EmailNotifyProduct.emailNotifyProduct_status_n);		List<EmailNotifyProduct> list = dao.selectList(info);				if(list!=null && list.size()>0){			List<String> productIDs = new LinkedList<String>();						for(int i=0;i<list.size();i++){				EmailNotifyProduct item = list.get(i);				productIDs.add(item.getProductID());			}						//如果商品已到货，则发出邮件.此处是否需要改造成检测内存中的商品库存数是否已到货呢？SystemManager.productStockMap.get(id).getStock()，内存库存数和数据库的稍微有一点点延迟			List<Product> productList = productService.selectStockByIDs(productIDs);						if(productList!=null && productList.size()>0){				for(int i=0;i<list.size();i++){					EmailNotifyProduct item = list.get(i);										for(int j=0;j<productList.size();j++){						Product pro = productList.get(j);						if(item.getProductID().equals(pro.getId())){							if(pro.getStock() > 0){								//发送到货邮件给用户								try {									notifyAccount(item);								} catch (Exception e) {									e.printStackTrace();								}								break;							}						}					}				}				}		}	}		/**	 * 邮件通知用户,商品已到货	 * @return	 */	private void notifyAccount(EmailNotifyProduct item){		logger.error("notifyAccount...");		com.tms.services.front.notifyTemplate.bean.NotifyTemplate notifyTemplate = systemManager.getNotifyTemplateMap().get(NotifyTemplate.email_notify_product);		if(notifyTemplate==null || StringUtils.isBlank(notifyTemplate.getTemplate())){			throw new NullPointerException("系统查询不到到货通知的邮件模板,请管理员立即处理!");		}				Account acc = new Account();		acc.setAccount(item.getAccount());		acc = accountService.selectOne(acc);		if(acc==null){			throw new NullPointerException("系统中查询不到会员信息:"+item.getAccount());		}				/**		 * 根据模板代号获取模板内容，然后封装数据后作为HTML内容发送到用户的邮箱		 */		Map<String,String> data = new HashMap<String, String>();		data.put("nickname", acc.getNickname());//会员昵称        SystemSetting systemSetting = systemManager.getSystemSetting();        data.put("system", systemSetting.getName());//系统名称		data.put("url", systemSetting.getWww()+"/product/"+item.getProductID()+".html");//到货通知的商品链接		data.put("servicesPhone", systemSetting.getTel());//系统客服电话		data.put("systemEmail", systemSetting.getEmail());//系统邮箱		data.put("helpUrl", "http://127.0.0.1:8080/help.html");//系统帮助地址		data.put("productID", "");//系统帮助地址		data.put("productName",item.getProductName());//商品名称		MailUtil mail = new MailUtil(item.getReceiveEmail());		try {			boolean result = mail.startSend("到货通知",FreemarkerTemplateUtil.freemarkerProcess(data,notifyTemplate.getTemplate()));			logger.error("email resule = " + result);			if(result){				//邮件发送成功				EmailNotifyProduct newItem = new EmailNotifyProduct();				newItem.setId(item.getId());				newItem.setNotifydate("-");//发送日期,now()				newItem.setStatus(EmailNotifyProduct.emailNotifyProduct_status_y);				dao.update(newItem);			}else{				sendEmailFailure(item);			}		} catch (Exception e1) {			e1.printStackTrace();						sendEmailFailure(item);		}	}	private void sendEmailFailure(EmailNotifyProduct item) {		logger.error("sendEmailFailure...");		//邮件发送失败		EmailNotifyProduct newItem = new EmailNotifyProduct();		newItem.setId(item.getId());		newItem.setDbFaildureAdd(true);		newItem.setStatus(EmailNotifyProduct.emailNotifyProduct_status_n);		dao.update(newItem);	}}