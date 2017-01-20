package com.tms.web.action.manage.task;import com.tms.core.TaskManager;import com.tms.core.dao.page.PagerModel;import com.tms.services.manage.task.TaskService;import com.tms.services.manage.task.bean.Task;import com.tms.web.action.BaseController;import com.tms.web.util.RequestHolder;import org.apache.commons.lang.StringUtils;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import javax.servlet.http.HttpServletRequest;@Controller@RequestMapping("task")public class TaskAction extends BaseController<Task> {	private static final long serialVersionUID = 1L;//	@Autowired	private TaskService taskService;//	@Autowired	private TaskManager taskManager;	public void setTaskManager(TaskManager taskManager) {		this.taskManager = taskManager;	}	public TaskService getService() {		return taskService;	}	public void insertAfter(Task e) {		e.clear();	}	@Override	protected void selectListAfter(PagerModel pager) {		super.selectListAfter(pager);		//获取每个任务的内存数据		if(pager.getList()!=null){			for(int i=0;i<pager.getList().size();i++){				Task item = (Task) pager.getList().get(i);				Task task = TaskManager.getTask(item.getCode());				if(task!=null){					item.setNextWorkTime(task.getNextWorkTime());					if(task.getThread()!=null){						item.setCurrentStatus(task.getThread().getState().name());					}else{						item.setCurrentStatus("已停止");					}				}			}		}	}	@Override	public String selectList(HttpServletRequest request, Task e) throws Exception {		return super.selectList(request, e);	}		/**	 * 立即执行一个任务	 * @return	 * @throws Exception 	 */	@RequestMapping("startTask")	public String startTask(Task e) throws Exception{		if(StringUtils.isBlank(e.getCode())){			throw new NullPointerException("任务代号不能为空！");		}		//		Task task = taskManager.getTask(e.getCode());//		if(task==null){//			throw new NullPointerException("系统中不存在此任务！");//		}		//		task.getThread().		return selectList(RequestHolder.getRequest(), e);	}	/**	 * 立即终止一个任务	 * @return	 * @throws Exception 	 */	@RequestMapping("stopTask")	public String stopTask(Task e) throws Exception{		if(StringUtils.isBlank(e.getCode())){			throw new NullPointerException("任务代号不能为空！");		}				return selectList(RequestHolder.getRequest(), e);	}}