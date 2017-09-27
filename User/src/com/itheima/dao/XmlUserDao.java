package com.itheima.dao;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.itheima.domain.User;
import com.itheima.util.XmlDaoUtils;

public class XmlUserDao implements UserDao {
	/**
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @return 根据用户名找到的用户信息bean,如果没找到返回null
	 */
	public User findUserByUserName(String username){
		//获得dom对象
		Document dom =  XmlDaoUtils.getDom();
		//在xml中查找具有username属性值==传入的用户名的元素
		//获取根元素
		Element root = dom.getRootElement();
		//泛型，不是很理解，晚上回去看书
		 List <Element>  list =  root.selectNodes("//user[@username = '"+username+"']");
		 System.out.println(list);
		 if(list.size()>0){//大于0说明找到了这个用户 
			//将找到的用户信息封装到bean后返回
			 Element userEle = list.get(0);
			 User user = new User();
			 user.setUsername(userEle.attributeValue("username"));
			 user.setUsername(userEle.attributeValue("password"));
			 user.setUsername(userEle.attributeValue("nickname"));
			 user.setUsername(userEle.attributeValue("Email"));
			 return user;
		 }
		 else {//说明没有找到这个用户
			return null;
		}	 	
}
	
	
	/**
	 * 添加用户
	 * @param user 要添加的用户信息bean
	 */
	public void addUser(User user){
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		//凭空创建出一个<user>元素,根据传入的user信息,设置此元素的属性
	    Element userEle = DocumentHelper.createElement("user");
		userEle.setAttributeValue("username",user.getUsername());
		userEle.setAttributeValue("password",user.getPassword());
		userEle.setAttributeValue("nickname",user.getNickname());
		userEle.setAttributeValue("email",user.getEmail());
		//挂载(写入)到<users>元素上
		root.add(userEle);
		//回写到xml文件中,关于是否传dom有疑问
		XmlDaoUtils.refXml();
	}

	
	/**
	 * 根据用户名密码查找对应的用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 找到的用户,如果找不到则返回null
	 */
	public User findUserByUNandPSW(String username,String password){
		//获得dom对象
		Document dom =  XmlDaoUtils.getDom();
		//在xml中查找具有username属性值==传入的用户名的元素
		//获取根元素
		Element root = dom.getRootElement();
	
		//泛型，不是很理解，晚上回去看书
		//在xml中查找具有username属性值==传入的用户名 并且 password==传入密码的元素
		
		//少写了@：List <Element>  list =  root.selectNodes("//user[@username = '"+username+"'and passwoed='"+password+"']");
		List<Element> list = root.selectNodes("//user[@username='"+username+"' and @password='"+password+"']");
		                                                                       
		 if(list.size()>0){//大于0说明找到了这个用户 
			//将找到的用户信息取第一个封装到bean后返回
			 Element userEle = list.get(0);
			 User user = new User();
			 //因为全部都setUsername而导致user为空，所以下次要注意set的时候不要复制.......
			 user.setUsername(userEle.attributeValue("username"));
			 user.setPassword(userEle.attributeValue("password"));
			 user.setNickname(userEle.attributeValue("nickname"));
			 user.setEmail(userEle.attributeValue("email"));
			 return user;

		 }
		 else {//说明没有找到这个用户
			return null;
		}
			 
	}
	
}

