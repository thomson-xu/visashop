/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package com.tms.services.manage.indexImg.dao;

import java.util.List;

import com.tms.core.DaoManager;
import com.tms.services.manage.indexImg.bean.IndexImg;


public interface IndexImgDao extends DaoManager<IndexImg> {

	/**
	 * @param i
	 * @return
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
