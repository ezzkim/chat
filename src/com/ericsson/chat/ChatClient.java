package com.ericsson.chat;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;

public class ChatClient extends Frame {

	TextField tfText = new TextField();
	TextArea taContent = new TextArea();
	
	public static void main(String[] args) {
		new ChatClient().lunchFrame();
	}
	
	public void lunchFrame() {
		this.setLocation(400,  300);
		this.setSize(300, 300);
		
		this.add(tfText, BorderLayout.SOUTH);
		this.add(taContent, BorderLayout.NORTH);
		this.pack();
		
		this.setVisible(true);
	}
	
}
