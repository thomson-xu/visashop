package com.tms.services.front.order;import java.util.List;import com.tms.core.Services;import com.tms.services.front.order.bean.Order;import com.tms.services.front.order.bean.OrderSimpleReport;import com.tms.services.front.orderdetail.bean.Orderdetail;import com.tms.services.front.ordership.bean.Ordership;public interface OrderService extends Services<Order> {	/**	 * 创建订单和订单项	 * @param order 订单对象	 * @param orderdetail  订单项对象	 * @return  成功返回true	 * @throws Exception	 */	public boolean createOrder(Order order, List<Orderdetail> orderdetailList,Ordership ordership)			throws Exception;	/**	 * 查询订单明细信息	 * @param order	 * @return	 */	public List<Order> selectOrderInfo(Order order);		/**	 * 修改订单状态，支付成功回调函数	 * @param order	 * @return	 *///	public boolean updateOrderStatus(Order order);		/**	 * 支付宝异步通知	 * @param trade_status 交易状态	 * @param refund_status	退款状态	 * @param out_trade_no 商户订单号	 * @param trade_no 支付宝交易号	 * 	 * 用户支付-->后台管理员审核订单-->后台管理员发货-->客户签收货物-->	 * 	 * 1、订单成功支付，则【已审核】按钮亮起	 * 2、管理员审核、管理员发货	 * 3、系统收到支付宝的异步回调通知，	 * 。。。。	 */	public boolean alipayNotify(String trade_status,String refund_status,String out_trade_no,String trade_no);	public OrderSimpleReport selectOrdersSimpleReport(String account);}