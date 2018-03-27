package com.liuyan.db;

import java.sql.*;

public class dbConnection {
	private final String driver="com.mysql.jdbc.Driver";
	private final String user="root";
	private final String password="liuyan19960818";
	private final String url="jdbc:mysql://localhost:3306/bankaccount?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private Connection con=null;
	private PreparedStatement prep=null;
	public dbConnection(){
		 try {
	            Class.forName(driver);
	            con=DriverManager.getConnection(url,user,password);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	//更新、插入，删除
    public void executeUpdate(String sql){
        try {
            prep=con.prepareStatement(sql);
            prep.executeUpdate();
        } catch (SQLException e) {
        	 e.printStackTrace();
        }
    }
    //查询
    public ResultSet executeQuery(String sql){
        ResultSet rs=null;
        try {
            prep=con.prepareStatement(sql);
            rs=prep.executeQuery();
        } catch (SQLException e) {
        	 e.printStackTrace();
        }
        return rs;
    }
    //关闭数据库连接
    public void close(){
        try {
            con.close();
            prep.close();
        } catch (SQLException e) {
        	 e.printStackTrace();
        }
    }
}
