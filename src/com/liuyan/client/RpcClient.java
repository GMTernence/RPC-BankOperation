package com.liuyan.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.liuyan.call.RpcRequest;
import com.liuyan.call.RpcResponse;


public class RpcClient {
	private String hostName;
	private int port;
	private Socket socket=null;
	public RpcClient(String hostName,int port) {
		this.hostName=hostName;
		this.port=port;
	}
	public Object invoke(RpcRequest request) throws UnknownHostException, IOException {
		ObjectInputStream ois=null;
		ObjectOutputStream oos=null;
		this.socket=new Socket(hostName,port);
		try {
			ois=new ObjectInputStream(socket.getInputStream());
			oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(request);
			/******************/
			Object object=ois.readObject();
			RpcResponse response=null;
			if(!(object instanceof RpcResponse)) {
				return new Exception("Wrong Paras");
			}else {
				response=(RpcResponse)object;
			}
			if(response.getError()!=null) {
				return response.getError();
			}else {
				return response.getResult();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null)  oos.close();
				if(ois!=null)  ois.close();
				if(socket!=null) socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
}
