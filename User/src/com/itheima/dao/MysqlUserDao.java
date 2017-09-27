/**
 * 
 */
package com.itheima.dao;

import com.itheima.domain.User;
import com.itheima.util.JDBCUtils;
import com.mysql.jdbc.ResultSet;

import java.sql.Connection;
import java.sql.Statement;

import javax.management.RuntimeErrorException;
import javax.management.j2ee.statistics.JDBCConnectionStats;
/**
 * @author ABRAM
 *
 */
public class MysqlUserDao implements UserDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from users where username='"+username+"' ";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//连接数据库
			conn = JDBCUtils.getConn();
			//创建数据对象
			stat = conn.createStatement();
			//执行操作
			rs = (ResultSet) stat.executeQuery(sql);
			//判断
			if(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));			
				return user;
			}else{
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{
			//关闭连接
			JDBCUtils.close(rs, stat, conn);
		}

	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String sql =  "insert into users values (null,'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getNickname()+"','"+user.getEmail()+"')";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//连接数据库
			conn = JDBCUtils.getConn();
			//创建数据对象
			stat = conn.createStatement();
			//执行操作
			stat.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{
			//关闭连接
			JDBCUtils.close(rs, stat, conn);
		}
	}

	@Override
	public User findUserByUNandPSW(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from users where username='"+username+"' and password='"+password+"'";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//连接数据库
			conn = JDBCUtils.getConn();
			//创建数据对象
			stat = conn.createStatement();
			//执行操作
			rs = (ResultSet) stat.executeQuery(sql);
			//判断
			if(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));			
				return user;
			}else{
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally{
			//关闭连接
			JDBCUtils.close(rs, stat, conn);
		}
	}

}
