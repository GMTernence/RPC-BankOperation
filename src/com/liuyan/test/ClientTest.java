package com.liuyan.test;

import java.util.Scanner;

import com.liuyan.call.RpcRequest;
import com.liuyan.client.RpcClient;

public class ClientTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RpcClient client=new RpcClient("127.0.0.1",8000);
		RpcRequest request=null;
		Object result=null;
		/*************************/
		Scanner scanner=new Scanner(System.in);
		while(true)
		{
			System.out.print("Account:");
			int account=Integer.parseInt(scanner.nextLine());
			System.out.print("Password:");
			int password=Integer.parseInt(scanner.nextLine());
			request=new RpcRequest("Service","LoginCheck",new Class<?>[]{int.class,int.class},new Object [] {
					account,password
			});
			result=client.invoke(request);
			if(result instanceof Integer) {  
				if((int)result==1) {
					System.out.println("Login Successfully");
					while(true) {
						showMenu();
						System.out.print("Input your choice:");
						int choice=Integer.parseInt(scanner.nextLine());
						switch(choice) {
						case 1: {
							request=new RpcRequest("Service","CheckBalance",new Class<?>[]{int.class},new Object [] {
									account
							});
							result=client.invoke(request);
							if(result instanceof Float) {
								System.out.println("balance:"+(float)result);
							}else {
								System.out.println("Server Error");
							}
						} break;
						case 2: {
							System.out.print("Input Withdraw Money:");
							float withdraw=Float.parseFloat(scanner.nextLine());
							request=new RpcRequest("Service","Deposit",new Class<?>[]{int.class,float.class},new Object [] {
									account,withdraw
							});
							result=client.invoke(request);
							if((int)result==0) {
								System.out.println("Server Error");
							}else if((int)result==2) {
								System.out.println("Withdraw Successfully!");
							}else {
								System.out.println("Balance Not Sufficient");
							}
						} break;
						case 3: {
							System.out.print("Input Another Account:");
							int another=Integer.parseInt(scanner.nextLine());
							System.out.print("Input Transfer Money:");
							float money=Float.parseFloat(scanner.nextLine());
							request=new RpcRequest("Service","transfer",new Class<?>[]{int.class,float.class,int.class},new Object [] {
									account,money,another
							});
							result=client.invoke(request);
							if((int)result==0) {
								System.out.println("Server Error");
							}else if((int)result==1) {
								System.out.println("Transfer Account Not Exit");
							}else if((int)result==2) {
								System.out.println("Balance Not Sufficient");
							}else {
								System.out.println("Transfer Successfully");
							}
						} break;
						case 4: {
							System.out.println("GoodBye");
							System.exit(0);
						}
						default: {
							System.out.println("Input Error");
							System.out.println("Input Again");
						}
						}
					}
				}else if((int)result==2) {
					System.out.println("Wrong Password");
					System.out.println("Input Again");
				}else {
					System.out.println("Account Not Exit");
					System.out.println("Input Again");
				}
			}
			else {
				System.out.println("Server Error");
			}
		}
	}
	public static void showMenu() {
		System.out.println("***************");
		System.out.println("*1 查询              *");
		System.out.println("*2 取款              *");
		System.out.println("*3 转账             *");
		System.out.println("*4 退出              *");
		System.out.println("***************");
	}
	
}
