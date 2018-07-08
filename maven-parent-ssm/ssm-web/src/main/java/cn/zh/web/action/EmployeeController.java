package cn.zh.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zh.domain.*;
import cn.zh.pojo.*;
import cn.zh.service.EmployeeService;

/**
 *
 * <p>
 * Title: EmployeeController.java<／p>
 * <p>
 * Description: 处理员工crud请求<／p>
 * <p>
 * Company: zh<／p>
 * 
 * @author zh
 * @date 2018年6月22日
 * @version 1.0
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	/**
	 * 查询员工数据(分页查询)
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/emps") public String getEmps(@RequestParam(value ="pn",
	 * defaultValue = "1") Integer pn, Model model) { // 引入PageHelper分页插件 //
	 * 在查询之间需要调用，传入页码，以及每页的大小 PageHelper.startPage(pn, 5); List<EmployeeCustom>
	 * emps = employeeService.getAll(); PageInfo page = new PageInfo(emps, 5);
	 * model.addAttribute("pageInfo", page); return "list";
	 * 
	 * }
	 */

	/**
	 * 查询员工数据(分页查询).使用@ResponseBody需要导入jackson包。
	 * 
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper分页插件
		// 在查询之间需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		List<EmployeeCustom> emps = employeeService.getAll();
		PageInfo page = new PageInfo(emps, 5);

		return Msg.success().add("PageInfo", page);
	}

	/**
	 * 员工信息保存 1、支持JSR303校验 2、导入hibernate-validator
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
				
			}
			return Msg.fail().add("fieldErrors", map);
			
		} else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}

	}

	/**
	 * 验证员工的唯一性
	 * 
	 * @param empnName
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public Msg checkUser(@RequestParam("empName") String empnName) {

		// 判断用户名的合法性
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!empnName.matches(regx)) {
			return Msg.fail().add("va_msg", "英文6-16,中文2-5");
		}

		boolean b = employeeService.checkuser(empnName);
		if (b) {
			return Msg.success();
		} else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}

	}
	
	/**
	 * 根据id查于员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
		
	  Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * tomcat默认封装post请求(请求的方式为key/value)的请求体为map,put请求不会封装。而springMVC
	 * 通过request.getParamter()给pojo对象中每个属性的赋值
	 * 得到。所以put请求拿不到数据。
	 * 员工更新方法.
	 * {empId}表示将这个位置的参数,传到@PathVariable指定名称中。
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(@PathVariable("empId") Integer id,@RequestBody Employee employee){
		
		employee.setEmpId(id);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 根据id删除单个
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("empId") Integer id){
		
		employeeService.deleteEmp(id);
		return Msg.success();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteEmpBatch",method=RequestMethod.DELETE)
	public Msg deleteEmpBatch(Integer[] ids){
		
		List<Integer> del_ids = new ArrayList<>();
		for (Integer id : ids) {
			del_ids.add(id);
		}
		
		employeeService.deleteBatch(del_ids);
		return Msg.success();
		
	}
}
