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
	 * �û����������ȷ����1
	 * �û������ڣ����벻��ȷ����2
	 * �û��������ڷ���3
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
	 * ȡ�����
	 * ȡ��������˻�������1
	 * ȡ��ɹ�����2
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
	 *ת�˲�����ת���˻������ڣ�����1
	 * ת�˽������˻�������2
	 * ת�˳ɹ�����3
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
