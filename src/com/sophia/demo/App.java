package com.sophia.demo;

import com.sophia.demo.controller.Index;
import com.sophia.demo.controller.UserController;
import com.tfss.sophia.Bootstrap;
import com.tfss.sophia.Sophia;
import com.tfss.sophia.db.SophiaDb;

public class App implements Bootstrap {

	@Override
	public void init(Sophia sophia) {
		
		try {
			// 解决tomcat下报错
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 配置数据库
		SophiaDb.init("jdbc:mysql://127.0.0.1:3306/sophia_demo", "root", "root");
		
		Index index = new Index();
		UserController userController = new UserController();
		sophia.addRouter("/", "index", index);
		sophia.addRouter("/hello", "hello", index);
		sophia.addRouter("/html", "html", index);
		
		sophia.addRouter("/users", "users", userController);
		sophia.addRouter("/user/add", "show_add", userController);
		sophia.addRouter("/user/save", "save", userController);
		sophia.addRouter("/user/edit", "edit", userController);
		sophia.addRouter("/user/update", "update", userController);
		sophia.addRouter("/user/del", "delete", userController);
		
		
		
	}
	
}
