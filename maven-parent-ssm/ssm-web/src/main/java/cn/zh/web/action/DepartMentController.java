package cn.zh.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zh.pojo.DepartmentCustom;
import cn.zh.pojo.Msg;
import cn.zh.service.*;

/**
 *
 * Title: DepartMentController.java Description: 部门信息处理 Company:zh
 * 
 * @author zh
 * @date 2018年7月2日
 * @version 1.0
 */
@Controller
public class DepartMentController {

	@Autowired
	private DepartMentService departMentService;

	/**
	 * 返回所有部门信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/depts")
	public Msg getDepts() {
		List<DepartmentCustom> list = departMentService.getDepts();
		return Msg.success().add("depts", list);
	}
}
