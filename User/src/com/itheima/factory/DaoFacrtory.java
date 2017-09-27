package com.itheima.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import com.itheima.dao.UserDao;

/**
 * @author ABRAM
 *工厂类
 */
public class DaoFacrtory {
	

	private static DaoFacrtory factory = new DaoFacrtory();     //单例模式
	private DaoFacrtory(){}		                                                            //私有化构造函数
	public static DaoFacrtory getFacrtory() {                                //get方法，只能有一个实例
		return factory;
	}
	
	
	private static Properties prop = null;  										//读配置文件	
	static{																								//静态代码块，只执行一次
		prop = new Properties();
		try {
			prop.load(new FileReader(DaoFacrtory.class.getClassLoader().getResource("config.properties").getPath())); //配置文件的路径
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 获取配置文件的UserDao,使得可以直接在配置文件中直接配置需要调用哪一个dao
 * @return dao对象
 */
	public UserDao getDao() {
		String clazz = prop.getProperty("UserDao");
		try {	
			return (UserDao)Class.forName(clazz).newInstance();//获取字节码对象并转换
		} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
		}	
	}
}
