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
 *���õĹ�����
 */
public class XmlDaoUtils {

	private static Document dom  = null;
	//ʹ�������������user.xmlĬ��·��
	private static String path = XmlDaoUtils.class.getClassLoader().getResource("users.xml").getPath();
	//˽�л����캯�����鿴����ģʽ
	private XmlDaoUtils(){}
	//����˽�ж���
	public  static Document getDom(){
		System.out.println(dom);
		return dom;
	}

	//��̬����飬ֱ�Ӽ���
	static{
		SAXReader reader = new SAXReader(); 
		try {
			//�����������·��,domָ��user.xml
			dom = reader.read(path);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


	//��Ԫ��д����xml��
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

