package com.rui.pro1.modules.sys.web;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
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
import com.rui.pro1.modules.sys.constants.enums.MenuStatusEnum;
import com.rui.pro1.modules.sys.entity.Department;
import com.rui.pro1.modules.sys.service.IDepartmentService;
import com.rui.pro1.modules.sys.vo.DepartmentVo;

/**
 * 用户管理
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
@Controller
@RequestMapping(SysUri.SYS_DEPARTMENT)
@MenuAnnot(id = SysMenu.SYS_DEPARTMENT, name = "部门管理", parentId = Modules.SYS, href = "/views/modules/sys/departmentlist", sortNo = 4,status=MenuStatusEnum.STOP_2)
public class DepartmentController extends SysBaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
			@RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize,
			DepartmentVo departmentVo) {
		ResultBean rb = new ResultBean();
	
			QueryResult<Department> result = departmentService
					.getDepartmentList(page, pagesize, departmentVo);
			rb.setData(result);
		
		return rb;

	}

	@PermissionAnnot(id = SysMenu.SYS_DEPARTMENT + ":get", name = "查询")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpRequest request, HttpResponse response,
			DepartmentVo departmentVo) {
		ResultBean rb = new ResultBean();
		
			Department department = departmentService.get(departmentVo.getId());
			rb.setData(department);
		
		return rb;
	}

	@PermissionAnnot(id = SysMenu.SYS_DEPARTMENT + ":del", name = "删除")
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpRequest request, HttpResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		
			int count = departmentService.del(id);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		
		return rb;
	}

	@PermissionAnnot(id = SysMenu.SYS_DEPARTMENT + ":add", name = "添加")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpRequest request, HttpResponse response,
			Department department) {
		ResultBean rb = new ResultBean();
		
			int count = departmentService.add(department);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		
		return rb;
	}

	@PermissionAnnot(id = SysMenu.SYS_DEPARTMENT + ":update", name = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpRequest request, HttpResponse response,
			Department department) {
		ResultBean rb = new ResultBean();
		
			int count = departmentService.update(department);
			if (count <= 0) {
				rb = new ResultBean(false, MessageCode.SYS_FAILURE, "操作失败");
			}
		
		return rb;
	}

}
