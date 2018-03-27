package com.liuyan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RpcServer {
    
	private ServerSocket server=null;
	private HashMap<String,Object> service=null;
	public RpcServer(int port) throws IOException {
		server=new ServerSocket(port);
	}
	//ע�����
	public void register(String className,Object object) {
		service=new HashMap<String,Object>();
		service.put(className, object);
	}
	public void invoke() throws IOException {
		System.out.println("�������������ȴ�����......");
		while(true) {
			Socket socket=server.accept();
			/*
			 * 
			 * 
			 * */
			ServerThread serverThread=new ServerThread(socket,service);
			Thread thread=new Thread(serverThread);
			thread.start();
			
		}
	}
}
