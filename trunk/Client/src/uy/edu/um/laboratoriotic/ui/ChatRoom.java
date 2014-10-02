package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class ChatRoom extends JFrame {

	private JPanel contentPane;
	private ImageIcon userPhotoImage;
	private JTextField messageText;
	private JLabel userPhotoLabel;
	private JLabel otherUserPhotoLabel;
	private JButton sendButton;
	private JLabel otherUserNameLabel;
	private JTextArea chatBox;
	private JSeparator separator;
	private JScrollPane scrollTextArea;

	
	public ChatRoom() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension d = new Dimension(500,500);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userPhotoImage = new ImageIcon("/Users/Luis/Downloads/InterfazGrafica/foto.png");
		
		
		otherUserPhotoLabel = new JLabel(userPhotoImage);
		otherUserPhotoLabel.setBounds(24, 57, 77, 77);
		contentPane.add(otherUserPhotoLabel);
		
		userPhotoLabel = new JLabel(userPhotoImage);
		userPhotoLabel.setBounds(24, 381, 77, 77);
		contentPane.add(userPhotoLabel);
		
		messageText = new JTextField("Message...");
		messageText.setBounds(113, 381, 296, 77);
		messageText.setForeground(Color.LIGHT_GRAY);
		//When focus changes color
		messageText.addFocusListener(new FocusListener() {
			
		    public void focusGained(FocusEvent e) {
		    	if(messageText.getText().equals("Message...")){
		    		messageText.setText("");
		    		messageText.setForeground(Color.BLACK);
		    	}
		    }

		    public void focusLost(FocusEvent e) {
		        if(messageText.getText().equals("")){
		        	messageText.setText("Message...");
		        	messageText.setForeground(Color.LIGHT_GRAY);
		        }
		    }
		});
		contentPane.add(messageText);
		messageText.setColumns(10);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(messageText.getText().length() < 1){
					
				}else if(!messageText.getText().equals("Message...")){
					chatBox.append(messageText.getText() + "\n");
					messageText.setForeground(Color.LIGHT_GRAY);
					messageText.setText("Message...");
					
					
				}
				
				
			}
		});
		sendButton.setBounds(417, 382, 49, 76);
		contentPane.add(sendButton);
		
		separator = new JSeparator();
		separator.setBounds(113, 357, 296, 12);
		contentPane.add(separator);
		
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		chatBox.setLineWrap(true);
		chatBox.setBounds(113, 82, 296, 263);
		contentPane.add(chatBox); 
		
		otherUserNameLabel = new JLabel("Other user name");
		otherUserNameLabel.setBounds(113, 57, 229, 16);
		contentPane.add(otherUserNameLabel);
		

	}
	
	public JFrame getFrame(){
		return this;
	}
}

