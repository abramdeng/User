/**
 * 
 */
package com.itheima.test;

import org.junit.Test;

import com.itheima.dao.XmlUserDao;
import com.itheima.util.*;
import com.itheima.domain.User;

/**
 * @author ABRAM
 *
 */
public class XmlUserDaoTest {

	@Test
	public void testFindUserByUserName(){
		XmlUserDao dao = new XmlUserDao();
		User user =  dao.findUserByUserName("1");
		System.out.println(user);
	}
	@Test
	public void testFindUserByNMandPSW(){
		XmlUserDao dao = new XmlUserDao();
		User user = dao.findUserByUNandPSW("123", "123");
		System.out.println(user);
	}
	
	@Test
	public void testAddUser(){
		XmlUserDao dao = new XmlUserDao();
		User user = new User();
		user.setUsername("123");
		user.setPassword("123");
		user.setNickname("Ð¡ÆÓÆÓ");
		user.setEmail("piaoqian@itcast.cn");
		dao.addUser(user);
	}

}
