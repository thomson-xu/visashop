package com.tms.services.front.comment.impl;import java.util.List;import com.tms.core.ServersManager;import com.tms.services.front.account.AccountService;import com.tms.services.front.comment.CommentService;import com.tms.services.front.comment.bean.Comment;import com.tms.services.front.order.bean.Order;import com.tms.services.front.order.dao.OrderDao;import com.tms.services.front.orderdetail.bean.Orderdetail;import com.tms.services.front.orderdetail.dao.OrderdetailDao;import com.tms.services.front.account.bean.Account;import com.tms.services.front.comment.dao.CommentDao;import org.springframework.stereotype.Service;import javax.annotation.Resource;@Service("commentServiceFront")public class CommentServiceImpl extends ServersManager<Comment, CommentDao> implements		CommentService {    @Resource(name = "commentDaoFront")    @Override    public void setDao(CommentDao commentDao) {        this.dao = commentDao;    }    @Resource(name = "orderdetailDaoFront")	private OrderdetailDao orderdetailDao;    @Resource(name = "orderDaoFront")	private OrderDao orderDao;    @Resource(name = "accountServiceFront")	private AccountService accountService;	public void setAccountService(AccountService accountService) {		this.accountService = accountService;	}	public void setOrderDao(OrderDao orderDao) {		this.orderDao = orderDao;	}	public void setOrderdetailDao(OrderdetailDao orderdetailDao) {		this.orderdetailDao = orderdetailDao;	}	/**	 * 插入评论，并同时更新指定的订单项为已评论。	 */	public int insertList(List<Comment> comments){		if(comments==null || comments.size()==0){			throw new NullPointerException();		}				for(int i=0;i<comments.size();i++){			Comment item = comments.get(i);			dao.insert(item);			//设置指定的订单项为			Orderdetail orderdetail = new Orderdetail();			orderdetail.setId(String.valueOf(item.getOrderdetailID()));			orderdetail.setIsComment("y");//设置为已评价			orderdetailDao.update(orderdetail);		}				//检查用户的所有订单项是否都已进行了评论，如果都评论过了，则更新订单的closedComment字段为y，表示订单评论功能关闭。		String orderid = comments.get(0).getOrderID();		int commentsCount = dao.selectCount(orderid);		int orderdetailCount = orderdetailDao.selectCount(orderid);		if(commentsCount > 0 && orderdetailCount==commentsCount){			Order order = new Order();			order.setId(String.valueOf(orderid));			order.setClosedComment(Order.order_closedComment_y);//关闭点评			orderDao.update(order);		}				//点评送积分。点评一个订单项则送该用户5个积分，此数字可以再后台进行配置		int addScore = comments.size() * 5;				Account acc = new Account();		acc.setAccount(comments.get(0).getAccount());		acc.setAddScore(addScore);		accountService.updateScore(acc);		return 1;	}}