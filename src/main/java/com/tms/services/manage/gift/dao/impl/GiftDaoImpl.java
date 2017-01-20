package com.tms.services.manage.gift.dao.impl;import java.util.List;import com.tms.core.dao.BaseDao;import com.tms.core.dao.page.PagerModel;import com.tms.services.manage.gift.bean.Gift;import com.tms.services.manage.gift.dao.GiftDao;import org.springframework.stereotype.Repository;import javax.annotation.Resource;@Repository("giftDaoManage")public class GiftDaoImpl implements GiftDao {    @Resource	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(Gift e) {		return dao.selectPageList("manage.gift.selectPageList",				"manage.gift.selectPageCount", e);	}	public List selectList(Gift e) {		return dao.selectList("manage.gift.selectList", e);	}	public Gift selectOne(Gift e) {		return (Gift) dao.selectOne("manage.gift.selectOne", e);	}	public int delete(Gift e) {		return dao.delete("manage.gift.delete", e);	}	public int update(Gift e) {		return dao.update("manage.gift.update", e);	}	public int deletes(String[] ids) {		Gift e = new Gift();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(Gift e) {		return dao.insert("manage.gift.insert", e);	}	public int deleteById(int id) {		return dao.delete("manage.gift.deleteById", id);	}	@Override	public Gift selectById(String id) {		return (Gift) dao.selectOne("manage.gift.selectById", id);	}}