package com.tms.web.action.manage.report;import com.alibaba.fastjson.JSON;import com.tms.core.Services;import com.tms.services.manage.order.bean.Order;import com.tms.services.manage.order.OrderService;import com.tms.services.manage.orderdetail.OrderdetailService;import com.tms.services.manage.product.ProductService;import com.tms.web.action.BaseController;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.ResponseBody;import java.io.IOException;import java.util.LinkedHashMap;import java.util.List;import java.util.Map;/** * 报表、图表 *  * @author huangf *  */@Controller@RequestMapping("/manage/report")public class ReportAction extends BaseController<ReportInfo> {	private static final long serialVersionUID = 1L;	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReportAction.class);	@Autowired	private ProductService	productService;	@Autowired	private OrderService orderService;	@Autowired	private OrderdetailService orderdetailService;	public void setOrderService(OrderService orderService) {		this.orderService = orderService;	}	public void setOrderdetailService(OrderdetailService orderdetailService) {		this.orderdetailService = orderdetailService;	}	@Override	public Services<ReportInfo> getService() {		return null;	}	public void setProductService(ProductService productService) {		this.productService = productService;	}	/**	 * 产品销售排行榜统计：图表的形式展示指定时间范围内的商品的销售情况，包括数量、金额等。	 * @return	 */	@RequestMapping("productSales")	public String productSales(){//		List<ReportInfo> list = orderdetailService.reportProductSales(new Orderdetail());//		return null;		return "manage/report/productSales";	}		/**	 * 销量统计：统计选择的时间范围内的销量情况。	 * @return	 */	@RequestMapping("orderSales")	public String orderSales(){//		logger.error("reportOrderSales....");//		try {//			Order order = new Order();//			order.setStartDate("2013-01");//			order.setEndDate("2014-05");//			List<ReportInfo> list = orderService.reportOrderSales(order);//			if(list==null){//				logger.error("reportOrderSales=0");//			}else{//				logger.error("reportOrderSales="+list.size());//			}//		} catch (Exception e) {//			e.printStackTrace();//		}		return "manage/report/orderSales";	}		/**	 * 查询指定时间范围内的订单的销量情况	 * @return	 * @throws java.io.IOException	 */	@RequestMapping("selectOrderSales")	@ResponseBody	public String selectOrderSales(ReportInfo e) throws IOException{		logger.error("selectOrderSales....startDate="+e.getStartDate()+",endDate="+e.getEndDate());//		try {//			Thread.sleep(5000L);//		} catch (InterruptedException e1) {//			e1.printStackTrace();//		}		Order order = new Order();		order.setStartDate(e.getStartDate());		order.setEndDate(e.getEndDate());//			if(StringUtils){//				order.setStartDate("2013-01");//				order.setEndDate("2014-05");//			}		List<ReportInfo> list = orderService.selectOrderSales(order);		if(list==null){			logger.error("reportOrderSales=0");		}else{			logger.error("reportOrderSales="+list.size());		}		if(list!=null && list.size()>0){			Map<String, String> map = new LinkedHashMap<String, String>();			StringBuilder amountBuff = new StringBuilder("[");			StringBuilder createdateBuff = new StringBuilder("[");			for(int i=0;i<list.size();i++){				ReportInfo item = list.get(i);				logger.error("item="+item.toString());				amountBuff.append(item.getSumAmount());				createdateBuff.append(item.getCreatedate());				if(i!=list.size()-1){					amountBuff.append(",");					createdateBuff.append(",");				}			}			amountBuff.append("]");			createdateBuff.append("]");						map.put("amountArr", amountBuff.toString());			map.put("orderdateArr", createdateBuff.toString());						logger.error("json="+JSON.toJSONString(map).toString());			return (JSON.toJSONString(map).toString());		}else{			return ("0");		}	}		/**	 * 查询指定时间范围内的产品的销量情况	 * @return	 * @throws java.io.IOException	 */	@RequestMapping("selectProductSales")	@ResponseBody	public String selectProductSales(ReportInfo e) throws IOException{		logger.error("selectProductSales....startDate="+e.getStartDate()+",endDate="+e.getEndDate());//		try {//			Thread.sleep(5000L);//		} catch (InterruptedException e1) {//			e1.printStackTrace();//		}		Order order = new Order();		order.setStartDate(e.getStartDate());		order.setEndDate(e.getEndDate());		List<ReportInfo> list = orderService.selectProductSales(order);		if(list==null){			logger.error("reportOrderSales=0");		}else{			logger.error("reportOrderSales="+list.size());		}		if(list!=null && list.size()>0){			Map<String, String> map = new LinkedHashMap<String, String>();			StringBuilder productSellCountBuff = new StringBuilder("[");			StringBuilder productNameBuff = new StringBuilder("[");//			for(int i=0;i<list.size();i++){			for(int i=list.size()-1;i>=0;i--){				ReportInfo item = list.get(i);//				logger.error("item="+item.toString());				productSellCountBuff.append(item.getProductSellCount());				productNameBuff.append("'"+item.getProductName()+"'");//				if(i!=list.size()-1){				if(i!=0){					productSellCountBuff.append(",");					productNameBuff.append(",");				}			}			productSellCountBuff.append("]");			productNameBuff.append("]");						map.put("productSellCountArr", productSellCountBuff.toString());			map.put("productNameArr", productNameBuff.toString());						logger.error("json="+JSON.toJSONString(map).toString());			return (JSON.toJSONString(map).toString());		}else{			return ("0");		}	}}