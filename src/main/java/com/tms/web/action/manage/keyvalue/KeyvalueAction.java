package com.tms.web.action.manage.keyvalue;import com.tms.core.dao.page.PagerModel;import com.tms.services.manage.keyvalue.KeyvalueService;import com.tms.core.oscache.FrontCache;import com.tms.services.manage.keyvalue.bean.Keyvalue;import com.tms.web.action.BaseController;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.servlet.http.HttpServletRequest;/** * 键值对管理 * @author dylan */@Controller@RequestMapping("/manage/keyvalue/")public class KeyvalueAction extends BaseController<Keyvalue> {	private static final long serialVersionUID = 1L;    @Autowired	private KeyvalueService keyvalueService;    @Autowired	private FrontCache frontCache;    private static final String page_toList = "/manage/keyvalue/keyvalueList";    private static final String page_toAdd = "/manage/keyvalue/keyvalueEdit";    private static final String page_toEdit = "/manage/keyvalue/keyvalueEdit";    KeyvalueAction(){        super.page_toList = page_toList;// not support        super.page_toAdd = page_toAdd;// not support        super.page_toEdit = page_toEdit;    }	public FrontCache getFrontCache() {		return frontCache;	}	public void setFrontCache(FrontCache frontCache) {		this.frontCache = frontCache;	}    @Override	public KeyvalueService getService() {		return keyvalueService;	}    @Override	protected void selectListAfter(PagerModel pager) {		pager.setPagerUrl("selectList");	}	public void setKeyvalueService(KeyvalueService keyvalueService) {		this.keyvalueService = keyvalueService;	}    @Override	public void insertAfter(Keyvalue e) {		e.clear();	}	@Override    @RequestMapping(value = "insert", method = RequestMethod.POST)	public String insert(HttpServletRequest request, Keyvalue e, RedirectAttributes flushAttrs) throws Exception {		super.insert(request, e, flushAttrs);//		KeyValueHelper.load(getPager().getList());		frontCache.loadKeyValue();//		return selectList();		return "redirect:selectList";	}	@Override    @RequestMapping(value = "update", method = RequestMethod.POST)	public String update(HttpServletRequest request, Keyvalue e, RedirectAttributes flushAttrs) throws Exception {		super.update(request, e, flushAttrs);//		KeyValueHelper.load(getPager().getList());		frontCache.loadKeyValue();		return "redirect:selectList";	}	}