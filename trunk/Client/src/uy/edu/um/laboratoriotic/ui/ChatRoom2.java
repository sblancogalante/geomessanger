package uy.edu.um.laboratoriotic.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class ChatRoom2 extends JFrame {

	private JPanel contentPane;
	private JTextField scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeVO oEmployee = new EmployeeVO("luisgur","Uruguay","programador",false);
					ChatRoom2 frame = new ChatRoom2(oEmployee);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatRoom2(EmployeeVO employee) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		Dimension d = new Dimension(500,500);
		this.setMinimumSize(d);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Files");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Send file...");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("FOTO");
		
		JLabel lblFoto = new JLabel("FOTO");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JSeparator separator = new JSeparator();
		
		JButton btnNewButton = new JButton("Send");
		
		JLabel lblNewLabel_1 = new JLabel("Chatting with " + employee.getName()+  " " + employee.getLastName());
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFoto, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
							.addComponent(lblFoto, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(44)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		
		final JTextArea messageTextArea = new JTextArea("Message...");
		messageTextArea.setForeground(Color.LIGHT_GRAY);
		
		//focus gained
		messageTextArea.addFocusListener(new FocusListener() {
			
		    public void focusGained(FocusEvent e) {
		    	if(messageTextArea.getText().equals("Message...")){
		    		messageTextArea.setText("");
		    		messageTextArea.setForeground(Color.BLACK);
		    	}
		    }

		    public void focusLost(FocusEvent e) {
		        if(messageTextArea.getText().equals("")){
		        	messageTextArea.setText("Message...");
		        	messageTextArea.setForeground(Color.LIGHT_GRAY);
		        }
		    }
		});
		
		
		scrollPane_2.setViewportView(messageTextArea);
		
		JTextArea converTextArea = new JTextArea();
		converTextArea.setEditable(false);
		scrollPane_1.setViewportView(converTextArea);
		contentPane.setLayout(gl_contentPane);
	}
}
