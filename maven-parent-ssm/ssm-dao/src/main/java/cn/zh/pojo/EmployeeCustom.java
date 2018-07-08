package cn.zh.pojo;

import cn.zh.domain.*;

/**
 *
 * <p>
 * Title: EmployeeCustom.java<／p>
 * <p>
 * Description: 员工扩展类<／p>
 * <p>
 * Company: zh<／p>
 * 
 * @author zh
 * @date 2018年6月20日
 * @version 1.0
 */
public class EmployeeCustom extends Employee {
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
