package com.ericsson.chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerBak {
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		DataInputStream dis = null;
		boolean started = false;
		boolean bConnected = false;
		try {
			ss = new ServerSocket(8888);
		}  catch (BindException e) {
			System.out.println("port is used");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			started = true;
			while(started) {
				s = ss.accept();
				System.out.println("a client connected");
				bConnected = true;
				dis = new DataInputStream(s.getInputStream());
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println("client say : " + str);
				}
				dis.close();
			}
		} catch (EOFException e) {
			System.out.println("Client logout");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dis!=null) dis.close();
				if(s!=null) s.close();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*private static void receiveMessage(final Socket s) {
		final DataInputStream dis = null;
		final boolean bConnected = true;
		
		Runnable run = new Runnable(){

			@Override
			public void run() {
				try {
					dis = new DataInputStream(s.getInputStream());
					while(bConnected) {
						String str = dis.readUTF();
						System.out.println("client say : " + str);
					}
					//dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		};
		
		new Thread(run).start();
	}
*/
}
