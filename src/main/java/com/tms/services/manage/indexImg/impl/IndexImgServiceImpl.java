/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package com.tms.services.manage.indexImg.impl;

import java.util.List;

import com.tms.core.ServersManager;
import com.tms.services.manage.indexImg.IndexImgService;
import com.tms.services.manage.indexImg.dao.IndexImgDao;
import com.tms.services.manage.indexImg.bean.IndexImg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author huangf
 */
@Service("indexImgServiceManage")
public class IndexImgServiceImpl extends ServersManager<IndexImg, IndexImgDao> implements
        IndexImgService {

    @Resource(name = "indexImgDaoManage")
    @Override
    public void setDao(IndexImgDao indexImgDao) {
        this.dao = indexImgDao;
    }

	@Override
	public List<IndexImg> getImgsShowToIndex(int i) {
		return dao.getImgsShowToIndex(i);
	}

}
