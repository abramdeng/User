
package com.itheima.domain;

import java.io.Serializable;

import com.itheima.exception.MsgException;

public class User implements Serializable {
	@Override
	public String toString() {
		return username+" "+password;
	}
	private String username;
	private String password;
	private String password2;
	private String nickname;
	private String email;
	private int id;
	public Integer getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//���������ֵ
	public void checkValue() throws MsgException{
		if(username==null || "".equals(username)){
			throw new MsgException("�û�������Ϊ��!");
		}
		if(password==null || "".equals(password)){
			throw new MsgException("���벻��Ϊ��!");
		}
		if(password2==null || "".equals(password2)){
			throw new MsgException("ȷ�����벻��Ϊ��!");
		}
		if(!password.equals(password2)){
			throw new MsgException("�������벻һ��!");
		}
		if(nickname==null || "".equals(nickname)){
			throw new MsgException("�ǳƲ���Ϊ��!");
		}
		if(email==null || "".equals(email)){
			throw new  MsgException("���䲻��Ϊ��!");
		}
		//xx@xx.xx������ʽ
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			throw new MsgException("�����ʽ����ȷ!");
		}
	}
}
