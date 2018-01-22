package com.sophia.demo.controller;

import com.tfss.sophia.servlet.Request;
import com.tfss.sophia.servlet.Response;

public class Index {
	
	public void index(Request request, Response response){
		request.setAttribute("name", "tfss");
		response.render("index");
	}
	
	public void hello(Request request, Response response){
		response.text("hello");
	}
	
	public void html(Request request, Response response){
		response.html("<h1>hello world!</h1>");
	}
	
}
