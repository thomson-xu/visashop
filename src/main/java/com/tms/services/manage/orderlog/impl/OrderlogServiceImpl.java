package com.tms.services.manage.orderlog.impl;import com.tms.core.ServersManager;import com.tms.services.manage.orderlog.OrderlogService;import com.tms.services.manage.orderlog.bean.Orderlog;import com.tms.services.manage.orderlog.dao.OrderlogDao;import org.springframework.stereotype.Service;import javax.annotation.Resource;@Service("orderlogServiceManage")public class OrderlogServiceImpl extends ServersManager<Orderlog, OrderlogDao> implements		OrderlogService {    @Resource(name = "orderlogDaoManage")    @Override    public void setDao(OrderlogDao orderlogDao) {        this.dao = orderlogDao;    }}