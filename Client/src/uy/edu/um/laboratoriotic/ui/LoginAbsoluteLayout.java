package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.io.File;
import java.io.IOException;




public class LoginAbsoluteLayout extends JFrame {
	
	private JPanel contentPane;
	private JLabel loginLabel;
	private JLabel imageLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton quitButton;
	private JButton loginButton;
	
	
	boolean logedin;
	
	private ImageIcon imageLogo;

	public LoginAbsoluteLayout(){
		
		logedin = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 300);
		Dimension d = new Dimension(200,300);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		loginLabel = new JLabel("Log in");
		loginLabel.setBounds(63, 6, 74, 28);
		loginLabel.setFont(new Font("Lucida Grande",Font.PLAIN,24));
		contentPane.add(loginLabel);
		
		
		userText = new JTextField("User name");
		userText.setForeground(Color.LIGHT_GRAY);
		userText.setBounds(33, 117, 134, 28);
		userText.setColumns(10);
		// User name text field,changes of color when foucs
		userText.addFocusListener(new FocusListener() {
			
		    public void focusGained(FocusEvent e) {
		    	if(userText.getText().equals("User name")){
		    		userText.setText("");
		    		userText.setForeground(Color.BLACK);
		    	}
		    }

		    public void focusLost(FocusEvent e) {
		        if(userText.getText().equals("")){
		        	userText.setText("User name");
		        	userText.setForeground(Color.LIGHT_GRAY);
		        }
		    }
		});
		contentPane.add(userText);
		
		
		passwordText = new JPasswordField("Password");
		passwordText.setForeground(Color.LIGHT_GRAY);
		passwordText.setBounds(33, 157, 134, 28);
		// Password text field,changes of color when foucs
		passwordText.addFocusListener(new FocusListener() {
			
		    public void focusGained(FocusEvent e) {
		    	if(String.valueOf(passwordText.getPassword()).equals("Password")){
		    		passwordText.setText("");
		    		passwordText.setForeground(Color.BLACK);
		    	}
		    }
  
		    public void focusLost(FocusEvent e) {
		    	
		    	 if(String.valueOf(passwordText.getPassword()).equals("")){
			        	passwordText.setText("Password");
			        	passwordText.setForeground(Color.LIGHT_GRAY);
			        }
		        
		    }
			
		});
		passwordText.setColumns(10);
		contentPane.add(passwordText);
		
		
		imageLogo = new ImageIcon("/Usders/Luis/Downloads/InterfazGrafica/logo.png");
		imageLabel = new JLabel(imageLogo);
		imageLabel.setBounds(70,40,60,60);
		contentPane.add(imageLabel);
		
	
		loginButton = new JButton("Log in");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logedin = true;
				Windows wind = new Windows();
				wind.setVisible(true);
				System.out.println("User name: " + userText.getText());
				System.out.println("Password: " + String.valueOf(passwordText.getPassword()));
				dispose();
				
			}
		});
		
		loginButton.setBounds(6, 198, 85, 28);
		contentPane.add(loginButton);
		
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(109, 198, 85, 28);
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		contentPane.add(quitButton);

	}
	
	public JFrame getFrame(){
		return this;
	}
	
	public Boolean getLogedin(){
		return logedin;
	}
	
	
}











