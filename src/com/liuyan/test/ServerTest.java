package com.liuyan.test;

import java.io.IOException;

import com.liuyan.server.RpcServer;
import com.liuyan.servive.ServiceImpl;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RpcServer server=new RpcServer(8000);
		server.register("Service", new ServiceImpl());
		server.invoke();
	}

}
