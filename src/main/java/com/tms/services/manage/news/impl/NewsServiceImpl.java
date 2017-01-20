/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package com.tms.services.manage.news.impl;

import java.util.List;

import com.tms.core.ServersManager;
import com.tms.services.manage.news.dao.NewsDao;
import com.tms.services.manage.news.NewsService;
import com.tms.services.manage.news.bean.News;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author huangf
 */
@Service("newsServiceManage")
public class NewsServiceImpl extends ServersManager<News, NewsDao> implements
		NewsService {
    @Resource(name = "newsDaoManage")
    @Override
    public void setDao(NewsDao newsDao) {
        this.dao = newsDao;
    }
	/**
	 * @param e
	 */
	public List<News> selecIndexNews(News e) {
		return dao.selecIndexNews(e);
	}

	@Override
	public void updateStatus(String[] ids, String status) {
		if(ids==null || ids.length==0){
			return;
		}
		
		for(int i=0;i<ids.length;i++){
			News news = new News();
			news.setId(ids[i]);
			news.setStatus(status);
			dao.sync(news);
		}
//		throw new NullPointerException();
	}

	@Override
	public void updateDownOrUp(News news) {
		dao.updateDownOrUp(news);
	}

	@Override
	public int selectCount(News news) {
		return dao.selectCount(news);
	}

}
