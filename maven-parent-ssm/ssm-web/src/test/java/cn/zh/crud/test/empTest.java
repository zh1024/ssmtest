package cn.zh.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

import cn.zh.domain.Employee;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * 
 * Title: MvcTest.java Description: 使用spring测试模块提供的测试请求功能,测试crud请求.
 * Spring4测试的时候，需要servlet3.0的支持
 *
 * Company: zh
 * 
 * @author zh
 * @date 2018年6月22日
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-*.xml", "classpath:springmvc.xml" })
public class empTest {

	@Autowired
	WebApplicationContext context;
	// 虚拟mvc请求，获取到处理结果
	MockMvc mockMvc;

	@Before
	public void initMokcMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		// json数据
		String jsonData = "{\"empId\":1,\"email\":\"tianaILoveYou1@qq.com\"}";
		// 模拟请求拿到返回值
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/emp/1").contentType("application/json").content(jsonData))
				.andReturn();

	}

	@Test
	public void deleteTest() throws Exception {
		// 根据id删除
		mockMvc.perform(MockMvcRequestBuilders.delete("/emp/4")).andDo(print()).andExpect(status().isBadRequest());
		;

	}

	@Test
	public void deleteBatchTest() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("ids", "5");
		params.add("ids", "6");

		// 根据id删除批量删除
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEmpBatch").params(params)).andDo(print())
				.andExpect(status().isBadRequest());
		;

	}
}
