package com.tms.services.front.order.dao;import java.util.List;import com.tms.services.front.order.bean.Order;import com.tms.services.front.order.bean.OrderSimpleReport;import com.tms.core.DaoManager;public interface OrderDao extends DaoManager<Order> {	List<Order> selectOrderInfo(Order order);	OrderSimpleReport selectOrdersSimpleReport(String account);}