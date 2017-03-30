package com.ericsson.chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	private ServerSocket ss = null;
	private boolean started = false;
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start() {
		try {
			ss = new ServerSocket(8866);
			started = true;
		}  catch (BindException e) {
			System.out.println("port is used");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// why can not write like below
		/**
		while(started) {
			try {
				Socket s = ss.accept();
				System.out.println("a client connected");
				Client client = new Client(s);
				new Thread(client).start();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(ss != null) ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		*/
		try {
			while(started) {
				Socket s = ss.accept();
				System.out.println("a client connected");
				Client client = new Client(s);
				new Thread(client).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ss != null) ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class Client implements Runnable {
		private Socket s = null;
		private DataInputStream dis = null;
		private boolean bConnected = false;
		
		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println("client say : " + str);
				}
			} catch (EOFException e) {
				System.out.println("Clinet close");
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
		
	}
}
