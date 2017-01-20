/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package com.tms.services.manage.indexImg;

import java.util.List;

import com.tms.core.Services;
import com.tms.services.manage.indexImg.bean.IndexImg;


/**
 * @author huangf
 */
public interface IndexImgService extends Services<IndexImg> {

	/**
	 * 加载图片显示到门户
	 * @param i
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
