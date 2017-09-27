package com.itheima.dao;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.itheima.domain.User;
import com.itheima.util.XmlDaoUtils;

public class XmlUserDao implements UserDao {
	/**
	 * �����û��������û�
	 * @param username �û���
	 * @return �����û����ҵ����û���Ϣbean,���û�ҵ�����null
	 */
	public User findUserByUserName(String username){
		//���dom����
		Document dom =  XmlDaoUtils.getDom();
		//��xml�в��Ҿ���username����ֵ==������û�����Ԫ��
		//��ȡ��Ԫ��
		Element root = dom.getRootElement();
		//���ͣ����Ǻ���⣬���ϻ�ȥ����
		 List <Element>  list =  root.selectNodes("//user[@username = '"+username+"']");
		 System.out.println(list);
		 if(list.size()>0){//����0˵���ҵ�������û� 
			//���ҵ����û���Ϣ��װ��bean�󷵻�
			 Element userEle = list.get(0);
			 User user = new User();
			 user.setUsername(userEle.attributeValue("username"));
			 user.setUsername(userEle.attributeValue("password"));
			 user.setUsername(userEle.attributeValue("nickname"));
			 user.setUsername(userEle.attributeValue("Email"));
			 return user;
		 }
		 else {//˵��û���ҵ�����û�
			return null;
		}	 	
}
	
	
	/**
	 * ����û�
	 * @param user Ҫ��ӵ��û���Ϣbean
	 */
	public void addUser(User user){
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		//ƾ�մ�����һ��<user>Ԫ��,���ݴ����user��Ϣ,���ô�Ԫ�ص�����
	    Element userEle = DocumentHelper.createElement("user");
		userEle.setAttributeValue("username",user.getUsername());
		userEle.setAttributeValue("password",user.getPassword());
		userEle.setAttributeValue("nickname",user.getNickname());
		userEle.setAttributeValue("email",user.getEmail());
		//����(д��)��<users>Ԫ����
		root.add(userEle);
		//��д��xml�ļ���,�����Ƿ�dom������
		XmlDaoUtils.refXml();
	}

	
	/**
	 * �����û���������Ҷ�Ӧ���û�
	 * @param username �û���
	 * @param password ����
	 * @return �ҵ����û�,����Ҳ����򷵻�null
	 */
	public User findUserByUNandPSW(String username,String password){
		//���dom����
		Document dom =  XmlDaoUtils.getDom();
		//��xml�в��Ҿ���username����ֵ==������û�����Ԫ��
		//��ȡ��Ԫ��
		Element root = dom.getRootElement();
	
		//���ͣ����Ǻ���⣬���ϻ�ȥ����
		//��xml�в��Ҿ���username����ֵ==������û��� ���� password==���������Ԫ��
		
		//��д��@��List <Element>  list =  root.selectNodes("//user[@username = '"+username+"'and passwoed='"+password+"']");
		List<Element> list = root.selectNodes("//user[@username='"+username+"' and @password='"+password+"']");
		                                                                       
		 if(list.size()>0){//����0˵���ҵ�������û� 
			//���ҵ����û���Ϣȡ��һ����װ��bean�󷵻�
			 Element userEle = list.get(0);
			 User user = new User();
			 //��Ϊȫ����setUsername������userΪ�գ������´�Ҫע��set��ʱ��Ҫ����.......
			 user.setUsername(userEle.attributeValue("username"));
			 user.setPassword(userEle.attributeValue("password"));
			 user.setNickname(userEle.attributeValue("nickname"));
			 user.setEmail(userEle.attributeValue("email"));
			 return user;

		 }
		 else {//˵��û���ҵ�����û�
			return null;
		}
			 
	}
	
}

