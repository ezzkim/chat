package com.ericsson.chat;

import java.awt.Frame;

public class ChatClient extends Frame {

	public static void main(String[] args) {
		new ChatClient().lunchFrame();
	}
	
	public void lunchFrame() {
		this.setLocation(400,  300);
		this.setSize(300, 300);
		this.setVisible(true);
	}
	
}
