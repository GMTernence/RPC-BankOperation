package com.liuyan.servive;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.liuyan.db.dbConnection;

public class ServiceImpl implements Service{
	
	private dbConnection conn=null;
	private ResultSet rs=null;
	private String sql=null;
	public ServiceImpl(){
		conn=new dbConnection();
	}
	
	/*
	 * 用户、密码均正确返回1
	 * 用户名存在，密码不正确返回2
	 * 用户名不存在返回3
	 * */
	@Override
	public int LoginCheck(int account, int password) {
		// TODO Auto-generated method stub
		sql="SELECT * FROM bank WHERE account ='"+account+"'";
		rs=conn.executeQuery(sql);
		try {
			if(rs.next()) {  
				int SQLpassword=rs.getInt("password");
				if (SQLpassword==password) {
					return 1;     
				}else {
					return 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 3;
	}

	@Override
	public float CheckBalance(int account) {
		// TODO Auto-generated method stub
		sql="SELECT * FROM bank WHERE account = '"+account+"' ";
		rs=conn.executeQuery(sql);
		try {
			if(rs.next()) {
				return rs.getFloat("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * 取款操作
	 * 取款金额大于账户余额，返回1
	 * 取款成功返回2
	 * */
	@Override
	public int Deposit(int account,float fund) {
		// TODO Auto-generated method stub
		sql="SELECT * FROM bank WHERE account = '"+account+"' ";
		rs=conn.executeQuery(sql);
		try {
			if(rs.next()) {
				float SQLbalance=rs.getFloat("balance");
				if(fund>SQLbalance) {
					return 1;
				}else {
					sql="UPDATE bank SET balance='"+(SQLbalance-fund)+"' WHERE account ='"+account+"'";
					conn.executeUpdate(sql);
					return 2;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/*
	 *转账操作，转账账户不存在，返回1
	 * 转账金额大于账户余额，返回2
	 * 转账成功返回3
	 * */
	@Override
	public int transfer(int account,float fund,int other) {
		sql="SELECT * FROM bank WHERE account = '"+account+"'";
		rs=conn.executeQuery(sql);
		try {
			if(rs.next()) {
				float SQLbalance=rs.getFloat("balance");
				if(fund>SQLbalance) {
					return 2;
				}else {
					sql="SELECT * FROM bank WHERE account = '"+other+"'";
					rs=conn.executeQuery(sql);
					if(rs.next()) {
						//
						float otherBalance=rs.getFloat("balance");
						sql="UPDATE bank SET balance = '"+(otherBalance+fund)+"' WHERE account = '"+other+"'";
						conn.executeUpdate(sql); 
						sql="UPDATE bank SET balance = '"+(SQLbalance-fund)+"' WHERE account = '"+account+"'";
						conn.executeUpdate(sql);
						return 3;
					}else {
						return 1;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
