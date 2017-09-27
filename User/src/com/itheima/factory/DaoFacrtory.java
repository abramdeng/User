package com.itheima.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import com.itheima.dao.UserDao;

/**
 * @author ABRAM
 *������
 */
public class DaoFacrtory {
	

	private static DaoFacrtory factory = new DaoFacrtory();     //����ģʽ
	private DaoFacrtory(){}		                                                            //˽�л����캯��
	public static DaoFacrtory getFacrtory() {                                //get������ֻ����һ��ʵ��
		return factory;
	}
	
	
	private static Properties prop = null;  										//�������ļ�	
	static{																								//��̬����飬ִֻ��һ��
		prop = new Properties();
		try {
			prop.load(new FileReader(DaoFacrtory.class.getClassLoader().getResource("config.properties").getPath())); //�����ļ���·��
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * ��ȡ�����ļ���UserDao,ʹ�ÿ���ֱ���������ļ���ֱ��������Ҫ������һ��dao
 * @return dao����
 */
	public UserDao getDao() {
		String clazz = prop.getProperty("UserDao");
		try {	
			return (UserDao)Class.forName(clazz).newInstance();//��ȡ�ֽ������ת��
		} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
		}	
	}
}
