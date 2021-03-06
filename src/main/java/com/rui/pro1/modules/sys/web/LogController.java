package com.rui.pro1.modules.sys.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.annotatiions.MenuAnnot;
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.constants.Modules;
import com.rui.pro1.common.constants.menu.SysMenu;
import com.rui.pro1.common.constants.uri.SysUri;
import com.rui.pro1.common.exception.MessageCode;
import com.rui.pro1.modules.sys.entity.SysLog;
import com.rui.pro1.modules.sys.service.ILogService;

@Controller
@RequestMapping(SysUri.SYS_LOG)
@MenuAnnot(id = SysMenu.SYS_LOG, name = "系统日志", parentId = Modules.SYS, href = "/views/modules/sys/log/loglist", sortNo = 6)
public class LogController extends SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ILogService logService;

	@PermissionAnnot(id = SysMenu.SYS_LOG + ":list", name = "查询列表")
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getUserList(
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "12") Integer pagesize,
			SysLog log) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<SysLog> result = logService.getList(page, pagesize, log);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}

//	@PermissionAnnot(id = SysMenu.SYS_LOG + ":get", name = "查看详情")
//	@RequestMapping(value = "get", method = RequestMethod.GET)
//	@ResponseBody
//	public ResultBean get(HttpServletRequest request,
//			HttpServletResponse response, Dict dict) {
//		ResultBean rb = new ResultBean();
//		try {
//			Dict result = dictService.get(dict.getId());
//			rb.setData(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
//		}
//		return rb;
//	}
//
//	@PermissionAnnot(id = SysMenu.SYS_LOG + ":del", name = "删除")
//	@RequestMapping(value = "del", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultBean del(HttpServletRequest request,
//			HttpServletResponse response,
//			@RequestParam(required = false, value = "id") Integer id) {
//		ResultBean rb = new ResultBean();
//		try {
//			int count = logService.del(id);
//			if (count <= 0) {
//				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			rb = new ResultBean(false, MessageCode.SYS_ERROR, "异统异常");
//		}
//		return rb;
//	}


}
