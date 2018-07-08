package cn.zh.dao;

import cn.zh.domain.*;
import cn.zh.pojo.*;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);
    /**
     * 根据条件查询员工和所在的部门信息
     * @param example 条件
     * @return
     */
    List<EmployeeCustom> selectByExampleWithDept(EmployeeExample example);
    /**
     * 根据id查询员工和所在部门的信息
     * @param empId 员工id
     * @return
     */
    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}