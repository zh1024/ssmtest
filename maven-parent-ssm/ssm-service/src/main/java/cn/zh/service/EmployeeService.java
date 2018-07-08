package cn.zh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zh.dao.EmployeeMapper;
import cn.zh.domain.*;
import cn.zh.domain.DepartmentExample.Criteria;
import cn.zh.pojo.*;

/**
 *
 * <p>
 * Title: EmployeeService.java<／p>
 * <p>
 * Description: <／p>
 * <p>
 * Company: zh<／p>
 * 
 * @author zh
 * @date 2018年6月22日
 * @version 1.0
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	public List<EmployeeCustom> getAll() {

		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		int insertSelective = employeeMapper.insertSelective(employee);
	}

	/**
	 * 检验用户名的唯一性
	 * 
	 * @param empnName
	 * @return
	 */
	public boolean checkuser(String empnName) {

		EmployeeExample example = new EmployeeExample();
		cn.zh.domain.EmployeeExample.Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empnName);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}

	/**
	 * 按照员工id查询
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	/**
	 * 员工的更新方法
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	/**
	 * 根据id删除员工信息
	 * @param id
	 */
	public void deleteEmp(Integer id) {
		
		employeeMapper.deleteByPrimaryKey(id);
		
	}

	/**
	 * 根据id批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer>  ids) {

		EmployeeExample  example = new EmployeeExample();
		cn.zh.domain.EmployeeExample.Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
