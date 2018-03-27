package com.liuyan.servive;

public interface Service {
	//登录检验
	public int LoginCheck(int account,int password);
	//查询余额
	public float CheckBalance(int account);
	//取款
	public int Deposit(int account,float fund);
	//转账
	public int transfer(int account,float fund,int other);
}
