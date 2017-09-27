/**
 * 
 */
package com.itheima.tag;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author ABRAM
 *
 */
public class URLDEcoderTag  extends SimpleTagSupport{
	private String content;
	private String encode;
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	@Override	
	 public void doTag() throws JspException,IOException {
		//拿出属性的值解码（内容，值）
		String str = URLDecoder.decode(content, encode == null ? "UTF-8" : encode);
		//输出流
		getJspContext().getOut().write(str);
		
	}
		
}
