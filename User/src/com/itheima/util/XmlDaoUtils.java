/**
 * 
 */
package com.itheima.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.sql.rowset.spi.XmlWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.kohsuke.rngom.dt.DoNothingDatatypeLibraryFactoryImpl;
import com.sun.xml.txw2.output.SaxSerializer;

/**
 * @author ABRAM
 *公用的工具类
 */
public class XmlDaoUtils {

	private static Document dom  = null;
	//使用类加载器返回user.xml默认路径
	private static String path = XmlDaoUtils.class.getClassLoader().getResource("users.xml").getPath();
	//私有化构造函数，查看单例模式
	private XmlDaoUtils(){}
	//返回私有对象
	public  static Document getDom(){
		System.out.println(dom);
		return dom;
	}

	//静态代码块，直接加载
	static{
		SAXReader reader = new SAXReader(); 
		try {
			//类加载器返回路径,dom指向user.xml
			dom = reader.read(path);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


	//将元素写出到xml中
	public static void refXml() {
		//??

		try {
			XMLWriter witer = new XMLWriter( new FileOutputStream(path),OutputFormat.createPrettyPrint());
			witer.write(dom);
			witer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

