package com.itheima.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.User;
import com.itheima.exception.MsgException;
import com.itheima.service.UserService;

public class RegistServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			UserService service = new UserService();
			//1.检验验证码
			String valistr = request.getParameter("valistr");
			String valistr2 = (String) request.getSession().getAttribute("valistr");
			if(valistr == null || valistr2==null || !valistr.equals(valistr2)){
				request.setAttribute("msg", "验证码不正确!");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}
			//2.封装数据校验数据
			User user = new User();
			/*user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setPassword2(request.getParameter("password2"));
			user.setNickname(request.getParameter("nickname"));
			user.setEmail(request.getParameter("email"));*/
			/*
			代替了get set方法，使用beanutils工具包，将信息封装进Userbean
			报错java.lang.ClassNotFoundException: org.apache.commons.beanutils.BeanUtils*/
			BeanUtils.populate(user, request.getParameterMap());
			//校验数据
			user.checkValue();
			//3.调用Service中的方法添加用户
			service.registUser(user);
			//4.登录用户
			request.getSession().setAttribute("user", user);
			//5.提示注册成功3秒回到主页
			response.getWriter().write("恭喜您注册成功!3秒回到主页....");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
		}catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
