/**
 * 
 */
package com.itheima.service;

import org.jaxen.function.StringFunction;

import com.itheima.dao.MysqlUserDao;
import com.itheima.dao.UserDao;
import com.itheima.dao.XmlUserDao;
import com.itheima.domain.User;
import com.itheima.exception.MsgException;
import com.itheima.factory.DaoFacrtory;

/**
 * @author ABRAM
 *
 */
public class UserService {

	//private MysqlUserDao dao = new MysqlUserDao();
	//XmlUserDao dao = new XmlUserDao();
	//根据配置文件调用dao
	private UserDao dao = DaoFacrtory.getFacrtory().getDao();
	/**
	 * 添加用户
	 * @param user
	 * @throws MsgException 
	 */
	public void registUser(User user) throws MsgException {
		//检查用户名是否已经存在，存在则提示
		if(dao.findUserByUserName(user.getUsername())!=null){
			throw new MsgException("用户名已存在！");
		}
		//不存在则添加用户
		dao.addUser(user);
}
	/**
	 * 检查用户名是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	public User isUser(String username,String password) {
	
		return dao.findUserByUNandPSW(username, password);
		
	}
}
