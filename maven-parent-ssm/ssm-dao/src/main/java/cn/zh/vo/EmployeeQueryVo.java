package cn.zh.vo;

import cn.zh.pojo.*;

/**
 *
 * <p>
 * Title: EmployeeQueryVo.java<／p>
 * <p>
 * Description: 员工包装类<／p>
 * <p>
 * Company: zh<／p>
 * 
 * @author zh
 * @date 2018年6月20日
 * @version 1.0
 */
public class EmployeeQueryVo {

	// 员工信息
	private EmployeeCustom employeeCustom;
	// 部门信息
	private DepartmentCustom departmentCustom;

	public EmployeeCustom getEmployeeCustom() {
		return employeeCustom;
	}

	public void setEmployeeCustom(EmployeeCustom employeeCustom) {
		this.employeeCustom = employeeCustom;
	}

	public DepartmentCustom getDepartmentCustom() {
		return departmentCustom;
	}

	public void setDepartmentCustom(DepartmentCustom departmentCustom) {
		this.departmentCustom = departmentCustom;
	}

}
