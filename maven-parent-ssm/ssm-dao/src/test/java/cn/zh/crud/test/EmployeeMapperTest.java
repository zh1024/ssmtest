package cn.zh.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zh.dao.DepartmentMapper;
import cn.zh.dao.EmployeeMapper;
import cn.zh.domain.Department;
import cn.zh.domain.Employee;

/**
 *
 * <p>
 * Title: EmployeeMapperTest.java<／p>
 * <p>
 * Description: <／p>
 * <p>
 * Company: zh<／p>
 * 
 * @author zh
 * @date 2018年6月20日
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class EmployeeMapperTest {

	// 基于xml测试方式
	/*
	 * private ApplicationContext applicationContext;
	 * 
	 * 
	 * @Before public void setUp() throws Exception { applicationContext = new
	 * ClassPathXmlApplicationContext(
	 * "classpath:spring/applicationContext-*.xml"); }
	 * 
	 * @Test public void test() { // EmployeeMapper employeeMapper
	 * =(EmployeeMapper)applicationContext.getBean(EmployeeMapper.class);
	 * System.out.println(applicationContext); }
	 */

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCurd() {
		/*
		 * Logger logger = Logger.getLogger(EmployeeMapperTest.class);
		 * logger.debug(" debug ");
		 */
		// System.out.println(departmentMapper);
		// 插入部门数据
		/*
		 * departmentMapper.insertSelective(new Department(null, "开发部"));
		 * departmentMapper.insertSelective(new Department(null, "测试部"));
		 */
		// 插入员工数据
		// employeeMapper.insertSelective(new Employee(null, "Jerry", "M",
		// "Jerry@qq.com",1));
		// 批量插入员工数据。可以执行批量操作的sqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@qq.com", 1));
		}
		System.out.println("批量完成");
	}
}
