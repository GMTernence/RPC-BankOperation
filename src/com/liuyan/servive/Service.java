package com.liuyan.servive;

public interface Service {
	//��¼����
	public int LoginCheck(int account,int password);
	//��ѯ���
	public float CheckBalance(int account);
	//ȡ��
	public int Deposit(int account,float fund);
	//ת��
	public int transfer(int account,float fund,int other);
}
