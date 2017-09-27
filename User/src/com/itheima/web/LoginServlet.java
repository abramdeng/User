package com.itheima.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.User;
import com.itheima.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		UserService service = new UserService();
		//1.获取客户端提交的用户名密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password);
		//2.调用Service中的方法检查用户名密码
		User user = service.isUser(username, password);
		if(user == null){
			//3.如果不正确则提示
			request.setAttribute("msg", "用户名密码不正确!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			//4.正确则登录用户后重定向回到主页
			request.getSession().setAttribute("user", user);
			//如果用户勾选了选项，返回ok
			if("ok".equals(request.getParameter("remname"))){
				//如果用户勾选过记住用户则发送cookie令浏览器保存用户名，使用URLEncoder编码将值赋给remname
				System.out.println(user.getUsername());
				Cookie remNameC = new Cookie("remname",URLEncoder.encode(user.getUsername(), "utf-8"));
				//当前web的路径
				remNameC.setPath(request.getContextPath());
				//保存1个月的cookie
				remNameC.setMaxAge(3600*24*30);
				//
				response.addCookie(remNameC);
			}else{
				//如果用户没有勾选记住用户名则删除记住用户名的cookie
				//传给remname空值
				Cookie remNameC = new Cookie("remname","");
				remNameC.setPath(request.getContextPath());
				remNameC.setMaxAge(0);
				response.addCookie(remNameC);
			}
			//重定向到主页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
