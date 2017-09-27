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
	//���������ļ�����dao
	private UserDao dao = DaoFacrtory.getFacrtory().getDao();
	/**
	 * ����û�
	 * @param user
	 * @throws MsgException 
	 */
	public void registUser(User user) throws MsgException {
		//����û����Ƿ��Ѿ����ڣ���������ʾ
		if(dao.findUserByUserName(user.getUsername())!=null){
			throw new MsgException("�û����Ѵ��ڣ�");
		}
		//������������û�
		dao.addUser(user);
}
	/**
	 * ����û����Ƿ���ȷ
	 * @param username
	 * @param password
	 * @return
	 */
	public User isUser(String username,String password) {
	
		return dao.findUserByUNandPSW(username, password);
		
	}
}
