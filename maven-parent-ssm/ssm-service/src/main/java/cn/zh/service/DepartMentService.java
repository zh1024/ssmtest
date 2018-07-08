package cn.zh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zh.dao.DepartmentMapper;
import cn.zh.pojo.DepartmentCustom;

/**
*
* Title: DepartMentService.java
* Description: 
* Company:zh
* @author zh
* @date 2018年7月2日
* @version 1.0
*/
@Service
public class DepartMentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<DepartmentCustom> getDepts() {
		List<DepartmentCustom> list = departmentMapper.selectByExample(null);
		return list;
	}

}
