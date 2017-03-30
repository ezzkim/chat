package com.ericsson.chat;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends Frame {

	private TextField tfText = new TextField();
	private TextArea taContent = new TextArea();
	private Socket s = null;
	private DataOutputStream dos = null;
	
	public static void main(String[] args) {
		new ChatClient().lunchFrame();
	}
	
	public void lunchFrame() {
		this.setLocation(400,  300);
		this.setSize(300, 300);
		
		this.add(tfText, BorderLayout.SOUTH);
		this.add(taContent, BorderLayout.NORTH);
		this.pack();
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		
		tfText.addActionListener(new TextFieldListener());
		
		this.setVisible(true);
		connect();//connect to server
	}
	
	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8866);
			dos = new DataOutputStream(s.getOutputStream());
			System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class TextFieldListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tfText.getText().trim();
			taContent.setText(str);
			tfText.setText("");
			
			try {
				//dos.writeBytes(str);
				dos.writeUTF(str);
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
