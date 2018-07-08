package cn.zh.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Title: Msg.java Description: 通用返回json的类 Company:zh
 * 
 * @author zh
 * @date 2018年7月2日
 * @version 1.0
 */
public class Msg {
	// 提示信息
	private String msg;
	// 返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String, Object>();
	// 状态
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * 返回成功信息
	 * 
	 * @return
	 */
	public static Msg success() {
		Msg result = new Msg();
		result.setStatus(true);
		result.setMsg("success");
		return result;
	}

	/**
	 * 失败
	 * 
	 * @return
	 */
	public static Msg fail() {
		Msg result = new Msg();
		result.setStatus(false);
		result.setMsg("fail");
		return result;
	}

	/**
	 * 添加请求数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}
