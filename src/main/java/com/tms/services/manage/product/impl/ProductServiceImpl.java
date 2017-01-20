package com.tms.services.manage.product.impl;import java.util.List;import com.tms.core.ServersManager;import com.tms.services.manage.product.ProductService;import com.tms.services.manage.product.bean.Product;import com.tms.services.manage.product.dao.ProductDao;import com.tms.services.manage.spec.SpecService;import com.tms.services.manage.spec.bean.Spec;import org.apache.commons.lang.StringUtils;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.stereotype.Service;import javax.annotation.Resource;@Service("productServiceManage")public class ProductServiceImpl extends ServersManager<Product, ProductDao> implements		ProductService {	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);    @Resource(name = "productDaoManage")    @Override    public void setDao(ProductDao productDao) {        this.dao = productDao;    }    @Resource(name = "specServiceManage")	private SpecService specService;	public SpecService getSpecService() {		return specService;	}	public void setSpecService(SpecService specService) {		this.specService = specService;	}	/**	 * 添加商品信息	 */	@Override	public int insert(Product e) {		int productID = super.insert(e);		insertOrUpdateSpec(e);		return productID;	}		/**	 * 更新商品信息	 */	@Override	public int update(Product e) {		super.update(e);		insertOrUpdateSpec(e);		return 1;	}		private void insertOrUpdateSpec(Product e){		if(e.getSpecList()!=null && e.getSpecList().size()>0){			for(int i=0;i<e.getSpecList().size();i++){				logger.error("=======insertOrUpdateSpec.e.getSpecArray() = " + e.getSpecList().get(i));				Spec spec = e.getSpecList().get(i);								if(StringUtils.isBlank(spec.getSpecColor())){					continue;				}								spec.setProductID(e.getId());				if(StringUtils.isBlank(spec.getId())){					specService.insert(spec);				}else{					specService.update(spec);				}			}		}else{			logger.error("=======insertOrUpdateSpec.e.getSpecArray() is null");		}		//		if(e.getSpecArray()!=null && e.getSpecArray().length>0){//			for(int i=0;i<e.getSpecArray().length;i++){//				logger.error("=======insertOrUpdateSpec.e.getSpecArray() = " + e.getSpecArray());//			}//		}else{//			logger.error("=======insertOrUpdateSpec.e.getSpecArray() is null");//		}//		specService.insertOrUpdateSpec(e);	}	/**	 * 批量删除商品	 * 包括:	 * 1、商品表数据。	 * 2、属性关联表数据。	 * 				3、商品图片。  这个不一定要删除，因为有可能同一张图片被多处引用了。	 * @param ids	 * @return	 */	public int deletes(String[] ids) {		if (ids != null && ids.length > 0) {			for (int i = 0; i < ids.length; i++) {				String id = ids[i];				logger.error("删除商品deleteById.id="+id);				if(StringUtils.isBlank(id)){					throw new NullPointerException();				}//				attributeDao.deleteById(Integer.parseInt(pid));//根据ID删除选择的属性								//根据PID删除选择属性下 的所有子属性//				attributeDao.deleteByPid(pid);								//删除attribute_link表的相关联的数据//				attribute_linkDao.de				//				attributeDao.deleteAllById(id);								dao.deleteById(Integer.parseInt(id));				dao.deleteAttributeLinkByProductID(Integer.parseInt(id));			}		}		return 0;	}	@Override	public void updateProductStatus(String[] ids, int status,String updateAccount) {		if (ids == null || ids.length == 0) {			throw new NullPointerException("商品ID不能为空！");		}				if(StringUtils.isBlank(updateAccount)){			throw new NullPointerException("操作人账号不能为空！");		}		for (int i = 0; i < ids.length; i++) {			if(StringUtils.isBlank(ids[i])){				throw new NullPointerException("商品ID不能存在空的！");			}						Product p = new Product();			p.setId(ids[i]);			p.setStatus(status);			p.setUpdateAccount(updateAccount);			dao.updateProductStatus(p);		}	}	@Override	public List<Product> selectStockByIDs(List<String> productIDs) {		return dao.selectStockByIDs(productIDs);	}	@Override	public int selectOutOfStockProductCount() {		return dao.selectOutOfStockProductCount();	}	@Override	public void updateImg(Product p) {		dao.updateImg(p);	}	@Override	public void updateProductBindActivityId(Product pro) {		dao.updateProductBindActivityId(pro);	}	@Override	public void updateResetThisProductActivityID(String activityID) {		dao.updateResetThisProductActivityID(activityID);	}}