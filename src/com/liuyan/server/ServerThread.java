package com.liuyan.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

import com.liuyan.call.RpcRequest;
import com.liuyan.call.RpcResponse;

public class ServerThread implements Runnable {
	private Socket socket=null;
	private HashMap<String,Object> services;
	ServerThread(Socket socket,HashMap<String,Object> services){
		super();
		this.socket=socket;
		this.services=services;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ObjectOutputStream oos=null;
		ObjectInputStream  ois=null;
		RpcResponse response=new RpcResponse();
		try {
			oos=new ObjectOutputStream(socket.getOutputStream());
			ois=new ObjectInputStream (socket.getInputStream());
			RpcRequest request=null;
			Object object=ois.readObject();
			if(!(object instanceof RpcRequest)) {
				response.setError(new Exception("Paras Error"));
				oos.writeObject(response);
				oos.flush();
				return ;
			}else {
				request=(RpcRequest) object;
			}
			String className=request.getClassName();
			String methodName=request.getMethodName();
			Class<?> [] parasType=request.getParasType();
			Object [] paras=request.getParas();
			Object service=services.get(className);
			if(service==null) {
				response.setError(new Exception(className +" not exit"));
				oos.writeObject(response);
				oos.flush();
				return ;
			}
			Class classType=service.getClass();
			Method method=classType.getMethod(methodName, parasType);
			Object result=method.invoke(service, paras);
			response.setResult(result);
			oos.writeObject(response);
			oos.flush();
			return ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(oos!=null) {
				response.setError(e);
				try {
					oos.writeObject(response);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
//			e.printStackTrace();
		}finally {
			try {
				if (oos!=null)
					oos.close();
				if (ois!=null)
					ois.close();
				if (socket!=null)
					socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
