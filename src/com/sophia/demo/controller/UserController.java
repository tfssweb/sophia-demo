package com.sophia.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sophia.demo.model.User;
import com.tfss.sophia.SophiaContext;
import com.tfss.sophia.db.SophiaDb;
import com.tfss.sophia.servlet.Request;
import com.tfss.sophia.servlet.Response;

/**
 * 用户控制器
 */
public class UserController {
	
	/**
	 * 用户列表
	 * @param request
	 * @param response
	 */
	public void users(Request request, Response response){
		List<User> users = SophiaDb.getList("select * from t_user", User.class);
		request.setAttribute("users", users);
		response.render("users");
	}
	
	/**
	 * 添加用户界面
	 * @param request
	 * @param response
	 */
	public void show_add(Request request, Response response){
		response.render("user_add");
	}
	
	/**
	 * 保存方法
	 * @param request
	 * @param response
	 * @throws ParseException
	 */
	public void save(Request request, Response response) throws ParseException{
		String name = request.getParameter("name");
		Integer age = request.getParameterAsInt("age");
		String date = request.getParameter("birthday");
		
		if(null == name || null == age || null == date){
			request.setAttribute("res", "error");
			response.render("user_add");
			return;
		}
		
		Date bir = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		
		int res = SophiaDb.insert("insert into t_user(name, age, birthday)", name, age, bir);
		if(res > 0){
			String ctx = SophiaContext.me().getContext().getContextPath();
			String location = ctx + "/users";
			response.redirect(location.replaceAll("[/]+", "/"));
		} else {
			request.setAttribute("res", "error");
			response.render("user_add");
		}
	}
	
	/**
	 * 编辑页面
	 * @param request
	 * @param response
	 */
	public void edit(Request request, Response response){
		Integer id = request.getParameterAsInt("id");
		if(null != id){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			User user = SophiaDb.get("select * from t_user where id = :id", User.class, map);
			request.setAttribute("user", user);
			response.render("user_edit");
		}
	}
	
	/**
	 * 修改信息
	 * @param request
	 * @param response
	 */
	public void update(Request request, Response response){
		Integer id = request.getParameterAsInt("id");
		String name = request.getParameter("name");
		Integer age = request.getParameterAsInt("age");
		
		if(null == id || null == name || null == age ){
			request.setAttribute("res", "error");
			response.render("user_edit");
			return;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("age", age);
		
		int res = SophiaDb.update("update t_user set name = :name, age = :age where id = :id", map);
		if(res > 0){
			String ctx = SophiaContext.me().getContext().getContextPath();
			String location = ctx + "/users";
			response.redirect(location.replaceAll("[/]+", "/"));
		} else {
			request.setAttribute("res", "error");
			response.render("user_edit");
		}
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	public void delete(Request request, Response response){
		Integer id = request.getParameterAsInt("id");
		if(null != id){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			SophiaDb.update("delete from t_user where id = :id", map);
		}
		
		String ctx = SophiaContext.me().getContext().getContextPath();
		String location = ctx + "/users";
		response.redirect(location.replaceAll("[/]+", "/"));
	}
}
